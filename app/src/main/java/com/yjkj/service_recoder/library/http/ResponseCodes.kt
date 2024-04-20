package com.yjkj.service_recoder.library.http

/**
 *@Created by houxiaoyao on 2022/3/28 11:18
 *  统一管理响应码
 */
object ResponseCodes{
    //登录接口成功
    const val LOGIN_SUCCESS = 0
    //成功
    const val SUCCESS = 200
    //用户名手机号不存在
    const val ACCOUNT_EXIST = 2001
    //数据异常，除了200就是500
    const val DATA_ERROR = 500
    //既登录状态失效
    const val TOKEN_INVALID = 401
}