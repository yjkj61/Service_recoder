package com.yjkj.service_recoder.library.http

import java.io.Serializable

/**
 *@Created by houxiaoyao on 2022/3/28 11:18
 * 统一处理请求数据，根据实际开发情况定义即可
 *登录接口专用
 */
class LoginDataResult : Serializable {
    var code = -1
    var msg : String = ""
    var token : String? = ""



    fun data():String?{
        when(ResponseCodes.LOGIN_SUCCESS){
            0 ->{
                return token
            }
            else ->{
                throw DataException(msg,code)
            }

        }
    }



}
