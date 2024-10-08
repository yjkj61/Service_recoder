package com.yjkj.service_recoder.java.entity

data class LoginEntity(
    val code: Int,
    val data: LoginData,
    val msg: String,
    val status: Int,
    val error: String
)

data class LoginData(
    val access_token: String,
    val expires_in: Int
)