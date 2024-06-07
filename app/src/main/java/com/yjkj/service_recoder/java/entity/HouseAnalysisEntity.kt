package com.yjkj.property_management.entity

/**
* @Author hxy
* Create at 2024/3/23
* @desc住户分析图
*/
data class HouseAnalysisEntity(
    var buildingNumber: Int,
    var overSixtyOwnerNumber: Int,
    var ownerNumber: Int,
    var solitaryElderlyNumber: Int
)
