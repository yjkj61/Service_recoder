package com.yjkj.service_recoder.java.data

import com.yjkj.service_recoder.java.http.MMKVUtils


object UserDataHelper {

    private const val token_key = "token"

    fun token():String{
        return MMKVUtils.decodeString(token_key)
    }

    fun token(value : String){
        MMKVUtils.encode(token_key,value)
    }

}