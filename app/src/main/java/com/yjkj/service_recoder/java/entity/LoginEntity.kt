package com.yjkj.service_recoder.java.entity

data class LoginEntity(
    val code: Int,
    val data: LoginData,
    val msg: String
)

data class LoginData(
    val access_token: String,
    val expires_in: Int
)