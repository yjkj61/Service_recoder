package com.yjkj.service_recoder.library.http

/**
 *@Created by houxiaoyao on 2022/3/28 16:12
 *
 */
class DataException(val errorMessage: String?, val errorCode: Int?) : Throwable()