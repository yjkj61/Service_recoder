package com.yjkj.service_recoder.library.http

object ApiContents {

    const val USER_BASE_URL = "http://47.116.36.196:9200/"
    const val BASE_URL = "http://47.116.36.196:9212/"
    const val BASE_URL_MANAGEMENT = "http://47.116.36.196:9500/"
    const val BASE_URL_SePropertyManagement = "http://47.116.36.196:9268/"
    const val BASE_URL_ORDER = "http://47.116.36.196:9889/"
    const val BASE_URL_Restaurant = "http://47.116.36.196:9233/"
    const val BASE_URL_contract = "http://47.116.36.196:9301/"
    const val BASE_URL_WARNING = "http://47.116.36.196:9203/"

    /**
     * 登录
     */
    const val login = "${USER_BASE_URL}login"

    //省市区
    const val requestArea = "${BASE_URL}area/detail"

    //住户分析图
    const val householdAnalysis = "${BASE_URL}ownerPropertyManagement/householdAnalysis"

    const val getHomePageData = "${BASE_URL}ownerPropertyManagement/homePage"

    //今日上班总人数
    const val employeesNumber = "${BASE_URL_MANAGEMENT}management/employeesNumber"
    //正在服务中人数
    const val servingNumber = "${BASE_URL_SePropertyManagement}SePropertyManagement/servingNumber"

    //今日物业工单
    const val todayServiceBook = "${BASE_URL_SePropertyManagement}SePropertyManagement/todayServiceBook"
    //5.今日商城订单数量
    const val todayGoodsOrder = "${BASE_URL_ORDER}order/todayGoodsOrder"
    //适老订单
    const val todayOldBook = "${BASE_URL_SePropertyManagement}SePropertyManagement/todayOldBook"
    //餐饮订单
    const val todayRestaurantOrder = "${BASE_URL_Restaurant}order/todayRestaurantOrder"
    //审批代办
    const val approvalAgencyCount = "${BASE_URL_ORDER}business/approvalAgencyCount"
    //到期合同数量
    const val searchExpireNumber = "${BASE_URL_contract}contract/searchExpireNumber"
    //点击充值 查询接口
    const val ownerList = "${BASE_URL}owner/list"
    //查询上班人数列表
    const val workList = "${BASE_URL_MANAGEMENT}worker/list"
    //查询服务中列表
    const val serviceList = "${BASE_URL_MANAGEMENT}worker/serviceList"
    //睡眠垫报警数量
    const val bedWarningNum = "${BASE_URL_WARNING}warn/warnNumber"
    //睡眠垫报警列表
    const val bedWarningList = "${BASE_URL}ownerPropertyManagement/bedWarnOwnerList"
    //物业工单list
    const val todayServiceBookList = "${BASE_URL_SePropertyManagement}SePropertyManagement/todayServiceBookList"
    //适老订单list
    const val todayOldBookList = "${BASE_URL_SePropertyManagement}SePropertyManagement/todayOldBookList"
    //商城订单list
    const val todayGoodsOrderList = "${BASE_URL_ORDER}order/todayGoodsOrderList"
    //餐饮订单list
    const val todayRestaurantOrderList = "${BASE_URL_Restaurant}order/todayRestaurantOrder"
    //second page
    const val secondPageOwnerList = "${BASE_URL}owner/list"
    //user info status
    var UserInfoStatus = "${BASE_URL}owner/"
}