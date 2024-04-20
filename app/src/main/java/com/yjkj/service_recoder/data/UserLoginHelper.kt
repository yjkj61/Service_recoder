package com.yjkj.service_recoder.data

import com.yjkj.service_recoder.library.utils.MMKVUtils

object UserLoginHelper {

    const val USER_NAME = "user_name"
    const val USER_PWD = "user_pwd"
    const val USER_ROOM_ID = "user_room_id"

    const val CHECKED_USERNAME = "CHECKED_USERNAME"
    const val CHECKED_PASSWORD = "CHECKED_PASSWORD"
    const val CHECKED_ROOMID = "CHECKED_ROOMID"

    fun username(var1 : String){
        MMKVUtils.encode(USER_NAME,var1)
    }

    fun username():String{
        return MMKVUtils.decodeString(USER_NAME)
    }

    fun userpwd(var1 : String){
        MMKVUtils.encode(USER_PWD,var1)
    }

    fun userpwd():String{
        return MMKVUtils.decodeString(USER_PWD)
    }

    fun userroomid(var1 : String){
        MMKVUtils.encode(USER_ROOM_ID,var1)
    }

    fun userroomid():String{
        return MMKVUtils.decodeString(USER_ROOM_ID)
    }

    fun checkedUsername(b : Boolean){
        MMKVUtils.encode(CHECKED_USERNAME,b)
    }
    fun checkedUsername():Boolean{
        return MMKVUtils.decodeBoolean(CHECKED_USERNAME,false)
    }

    fun checkedPassword(b : Boolean){
        MMKVUtils.encode(CHECKED_PASSWORD,b)
    }
    fun checkedPassword():Boolean{
        return MMKVUtils.decodeBoolean(CHECKED_PASSWORD,false)
    }

    fun checkedRoomId(b : Boolean){
        MMKVUtils.encode(CHECKED_ROOMID,b)
    }
    fun checkedRoomId():Boolean{
        return MMKVUtils.decodeBoolean(CHECKED_ROOMID,false)
    }

}