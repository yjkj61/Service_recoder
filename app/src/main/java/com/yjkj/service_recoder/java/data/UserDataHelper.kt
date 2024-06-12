package com.yjkj.service_recoder.java.data

import com.yjkj.service_recoder.java.http.MMKVUtils


object UserDataHelper {

    private const val token_key = "token"

    private const val username_key = "username"

    private const val password_key = "password"

    private const val roomnumber_key = "roomnumber"

    fun token():String{
        return MMKVUtils.decodeString(token_key)
    }

    fun token(value : String){
        MMKVUtils.encode(token_key,value)
    }

    fun username():String{
        return MMKVUtils.decodeString(username_key)
    }

    fun username(value : String){
        MMKVUtils.encode(username_key,value)
    }

    fun password():String{
        return MMKVUtils.decodeString(password_key)
    }

    fun password(value : String){
        MMKVUtils.encode(password_key,value)
    }

    fun roomNumber():String{
        return MMKVUtils.decodeString(roomnumber_key)
    }

    fun roomNumber(value : String){
        MMKVUtils.encode(roomnumber_key,value)
    }

}