package com.yjkj.property_management.entity




data class AreaData(
    var count: String = "",
    var districts: List<Country> = listOf(),
    var info: String = "",
    var infocode: String = "",
    var status: String = "",
    var suggestion: Suggestion = Suggestion()
)

//国
data class Country(
    var adcode: String = "",
    var center: String = "",
    var districts: List<Province> = listOf(),
    var level: String = "",
    var name: String = ""
)

data class Suggestion(
    var cities: List<Any> = listOf(),
    var keywords: List<Any> = listOf()
)

//省
data class Province(
    var adcode: String = "",
    var center: String = "",
    var districts: List<City> = listOf(),
    var level: String = "",
    var name: String = ""
)

//市
data class City(
    var adcode: String = "",
    var center: String = "",
    var citycode: Any? = null,
    var districts: List<District> = listOf(),
    var level: String = "",
    var name: String = ""
)

//区
data class District(
    var adcode: String = "",
    var center: String = "",
    var citycode: Any? = null,
    var districts: List<Any> = listOf(),
    var level: String = "",
    var name: String = ""
)