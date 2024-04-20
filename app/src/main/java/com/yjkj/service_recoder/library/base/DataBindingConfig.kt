package com.yjkj.service_recoder.library.base

import android.util.SparseArray
import androidx.lifecycle.ViewModel

class DataBindingConfig(
    val layout: Int,
    val vmVariableId: Int,
    val stateViewModel: ViewModel
) {
    private val bindingParams = SparseArray<Any>()

    fun getBindingParams(): SparseArray<Any> {
        return bindingParams
    }

    fun addBindingParam(variableId: Int, obj: Any): DataBindingConfig {
        if (bindingParams.get(variableId) == null) {
            bindingParams.put(variableId, obj)
        }
        return this
    }
}