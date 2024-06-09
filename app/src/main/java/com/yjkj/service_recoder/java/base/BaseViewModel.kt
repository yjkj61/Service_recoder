package com.yjkj.service_recoder.java.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yjkj.service_recoder.java.utils.log
import com.yjkj.service_recoder.java.utils.toast
import com.yjkj.service_recoder.library.http.DataException
import com.yjkj.service_recoder.library.http.DataResult
import com.yjkj.service_recoder.library.http.LoginDataResult
import com.yjkj.service_recoder.library.http.ResponseCodes
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import org.json.JSONException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException


open class BaseViewModel : ViewModel() {

    /**
     * 网络请求loading的状态
     */
    private var _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading
    private fun setLoading(isLoading: Boolean) {
        if (!isLoading) {
            viewModelScope.launch {
                delay(500)
                _isLoading.value = isLoading
                setRefreshState(isLoading)
            }

        } else {
            _isLoading.value = isLoading
            setRefreshState(isLoading)
        }
    }

    /**
     * 页面缺省页状态
     */
    private var _screenPlaceHolderState = MutableStateFlow(ScreenPlaceHolderState.SUCCESS)
    val screenPlaceHolderState: StateFlow<ScreenPlaceHolderState> = _screenPlaceHolderState
    private fun setPlaceHolderState(state: ScreenPlaceHolderState) {
        _screenPlaceHolderState.value = state
    }

    /**
     * 当页面状态为ERROR时的原因
     */
    private var _errorReasonState = MutableStateFlow("")
    val errorReasonState: StateFlow<String> = _errorReasonState
    private fun setErrorReasonState(state: String) {
        _errorReasonState.value = state
    }


    private var _refreshState = MutableStateFlow(false)
    val refreshState: StateFlow<Boolean> = _refreshState
    private fun setRefreshState(refresh: Boolean) {
        _refreshState.value = refresh
    }

    /**
     * @param enableLoading : 允许loading
     * @param flowOf : 网络请求代码块
     * @param onSuccess : 请求成功
     * @param onDataEmpty : 请求数据为空
     * @param onError : 请求失败
     */
    @OptIn(InternalCoroutinesApi::class)
    fun <T> launch(
        flowOf: suspend () -> Flow<DataResult<T>>,
        onSuccess: (data: T) -> Unit = {},
        onDataEmpty: (msg:String) -> Unit = {},
        onError: (exception: DataException) -> Unit = {},
        enableLoading: Boolean = false
    ): Job {
        return viewModelScope.launch(Dispatchers.IO) {
            setLoading(enableLoading)
            setPlaceHolderState(ScreenPlaceHolderState.LOADING)
            kotlin.runCatching {
                flowOf.invoke().collect {
                    it.let {
                        when (it.code) {
                            ResponseCodes.SUCCESS -> {
                                if (it.data() == null) {
                                    runOnUiThread {
                                        onDataEmpty.invoke(it.msg)
                                        setPlaceHolderState(ScreenPlaceHolderState.DATA_EMPTY)
                                        setLoading(false)
                                    }

                                } else {
                                    if (it.data() is List<*>) {
                                        if ((it.data() as List<*>).isEmpty()) {
                                            runOnUiThread {
                                                onDataEmpty.invoke(it.msg)
                                                setPlaceHolderState(ScreenPlaceHolderState.DATA_EMPTY)
                                                setLoading(false)
                                            }
                                        } else {
                                            runOnUiThread {
                                                it.data()?.let(onSuccess)
                                                setPlaceHolderState(ScreenPlaceHolderState.SUCCESS)
                                                setLoading(false)
                                            }

                                        }
                                    } else {
                                        runOnUiThread {
                                            it.data()?.let(onSuccess)
                                            setPlaceHolderState(ScreenPlaceHolderState.SUCCESS)
                                            setLoading(false)
                                        }

                                    }
                                }
                            }

                            else -> {
                                val dataException = DataException(it.msg, it.code)
                                runOnUiThread {
                                    toast("${dataException.errorMessage}")
                                    onError.invoke(dataException)
                                    setPlaceHolderState(ScreenPlaceHolderState.ERROR)
                                    setErrorReasonState("${dataException.errorMessage}")
                                    setLoading(false)
                                }

                            }
                        }
                    }
                }
            }.onFailure {
                getException(it).apply {
                    this?.let {
                        runOnUiThread {
                            "${it.errorMessage}".log("Okhttp")
                            toast("${it.errorMessage}")
                            onError.invoke(it)
                            setPlaceHolderState(ScreenPlaceHolderState.ERROR)
                            setErrorReasonState("${it.errorMessage}")
                            setLoading(false)
                        }

                    }

                }
            }
        }
    }


    /**
     * 登录接口专用
     */
    fun launchLogin(
        flowOf: suspend () -> Flow<LoginDataResult>,
        onSuccess: (data: String) -> Unit = {},
        onDataEmpty: () -> Unit = {},
        onError: (exception: DataException) -> Unit = {},
        enableLoading: Boolean = false,
    ): Job {
        return viewModelScope.launch(Dispatchers.IO) {
            setLoading(enableLoading)
            kotlin.runCatching {
                flowOf.invoke().collect {
                    it.let {
                        when (it.code) {
                            ResponseCodes.LOGIN_SUCCESS -> {
                                runOnUiThread {
                                    it.data()?.let(onSuccess)
                                    setLoading(false)
                                }
                            }

                            else -> {
                                val dataException = DataException(it.msg, it.code)
                                toast("${dataException.errorMessage}")
                                onError.invoke(dataException)
                                setLoading(false)
                            }
                        }
                    }
                }
            }.onFailure {
                getException(it).apply {
                    this?.let {
                        toast("${it.errorMessage}")
                        onError.invoke(it)
                        setLoading(false)
                    }

                }
            }
        }
    }


    /**
     * 捕获异常信息
     */
    private fun getException(throws: Throwable): DataException? {
        return when (throws) {
            is UnknownHostException -> {
                DataException("网络不给力", -100)
            }

            is JSONException -> {//|| e is JsonParseException
                DataException("数据异常", -100)
            }

            is SocketTimeoutException -> {
                DataException("连接超时", -100)
            }

            is ConnectException -> {
                DataException("连接错误", -100)
            }

            is retrofit2.HttpException -> {
                DataException("HTTP CODE ${throws.code()}", -100)
            }

            is DataException -> {
                throws
            }

            else -> {
                DataException("未知错误:" + throws.message, -1000)
            }
        }
    }

    fun withDelay(delay: Long = 500,dispatcher: CoroutineDispatcher = Dispatchers.IO, block: () -> Unit) {
        viewModelScope.launch(dispatcher) {
            delay(delay)
            block.invoke()
        }
    }

    fun runOnUiThread(block: () -> Unit) {
        viewModelScope.launch(Dispatchers.Main) {
            block.invoke()
        }
    }

    fun withIO(block : ()->Unit){
        viewModelScope.launch(Dispatchers.IO) {
            block.invoke()
        }
    }

    fun withDefault(block: () -> Unit){
        viewModelScope.launch(Dispatchers.Default) {
            block.invoke()
        }
    }


    fun launch(block: suspend () -> Unit) {
        viewModelScope.launch {
            block.invoke()
        }
    }

    fun debounce(block : ()->Unit){
        viewModelScope.launch {
            flow {
                emit(Any())
            }.debounce(500).collect{
                block.invoke()
            }
        }
    }

}




/**
 * @Author hxy
 * Create at 2023/9/6
 * @desc 缺省页状态
 */
enum class ScreenPlaceHolderState {
    /**
     * 数据加载中
     */
    LOADING,

    /**
     * 加载成功
     */
    SUCCESS,

    /**
     * 加载失败
     */
    ERROR,

    /**
     * 返回数据为空
     */
    DATA_EMPTY
}


