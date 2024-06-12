package com.yjkj.service_recoder.java.bean

import com.yjkj.service_recoder.java.entity.OwnerEntity

/**
 * @description 房间列表
 * @author: Lenovo
 * @date: 2024/6/12
 */
data class OwnerEntityResultEntity(
    val code: Int,
    val data: OwnerEntity,
    val msg: String
)