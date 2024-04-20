package com.yjkj.service_recoder.library.base

import com.yjkj.service_recoder.library.http.DataResult
import com.yjkj.service_recoder.library.http.LoginDataResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

/**
 *@Created by houxiaoyao on 2022/3/29 11:19
 *
 */
open class BaseRepository {

    /**
     * 在协程作用域中切换至IO线程
     */
    protected suspend fun <T> withIO(block: suspend () -> T): T {
        return withContext(Dispatchers.IO) {
            block.invoke()
        }
    }

    /**
     * 网络请求返回flow
     */
    protected suspend fun <T> flowOf(block: suspend () -> DataResult<T>) : Flow<DataResult<T>> {
        return flow{
            emit(block.invoke())
        }
    }

    /**
     * 登录接口专用
     */
    protected suspend fun loginFlowOf(block : suspend () -> LoginDataResult) : Flow<LoginDataResult>{
        return flow{
            emit(block.invoke())
        }
    }




}