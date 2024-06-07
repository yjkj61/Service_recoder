package com.yjkj.service_recoder.java.http;

public class API {

    public static String USER_BASE_URL = "http://47.116.36.196:9200/";
    public static String BASE_URL = "http://47.116.36.196:9212/";
    public static String BASE_URL_9213 = "http://47.116.36.196:9213/";
    public static String BASE_URL_MANAGEMENT = "http://47.116.36.196:9500/";
    public static String BASE_URL_SePropertyManagement = "http://47.116.36.196:9268/";
    public static String BASE_URL_ORDER = "http://47.116.36.196:9889/";
    public static String BASE_URL_Restaurant = "http://47.116.36.196:9233/";
    public static String BASE_URL_contract = "http://47.116.36.196:9301/";
    public static String BASE_URL_WARNING = "http://47.116.36.196:9203/";


    public static String owner(String ownerId) {
        return BASE_URL + "owner/ownerId?ownerId=" + ownerId;
    }

    public static String addOwnerInfo = BASE_URL + "/owner/addOwnerInfo";
    public static String editOwnerInfo = BASE_URL + "/owner/editOwnerInfo";

    public static String medicalHistory = BASE_URL + "/profile/list/history";

    public static String requestFamily = BASE_URL + "/member/getAndroidFamilyList";

    //物业工单
    public static String PropertyOrderList = BASE_URL_SePropertyManagement + "SePropertyManagement/todayServiceBookList?";

    //适老工单
    public static String OldOrderList = BASE_URL_SePropertyManagement + "SePropertyManagement/todayOldBookList?";

    //商城工单
    public static String ShopOrderList = BASE_URL_ORDER + "order/list?isToday=1&";

    //餐饮工单
    public static String FoodOrderList = BASE_URL_Restaurant + "order/todayRestaurantOrderList?";

    //审批待办-商家代办
    public static String DealWithList1 = BASE_URL_ORDER + "business/list?processStatus=1&";

    //审批待办-服务商代办
    public static String DealWithList2 = BASE_URL_SePropertyManagement + "agent/androidList?agentCheckStatus=0&";

    //审批待办-项目代办
    public static String DealWithList3 = BASE_URL_ORDER + "business/list?processStatus=1&";

    //合同列表
    public static String ContractList = BASE_URL_contract + "contract/list?greaterThanPersonnelContract=";

    //SOS
    public static String SOS = BASE_URL + "order/todayRestaurantOrder?";

    //护理
    public static String HL = BASE_URL + "order/todayRestaurantOrder?";

    //其他服务
    public static String OTHER = BASE_URL + "order/todayRestaurantOrder?";

    //尿不湿报警
    public static String NBS = BASE_URL + "ownerPropertyManagement/urinaryCushionOwnerList?";

    //安防报警
    public static String AF = BASE_URL_ORDER + "hardware/androidList?";

    //电子围栏报警
    public static String DZWL = BASE_URL + "order/todayRestaurantOrder?";

    //睡眠垫
    public static String sleep = BASE_URL + "ownerPropertyManagement/bedWarnOwnerList?";

    //查房报告-获取问题
    public static String CFBG_GET_QUESTIONS = BASE_URL_9213 + "inspectionAnswer/selectAnswerList?";

    //查房报告-新增报告
    public static String CFBG_ADD_QUESTIONS = BASE_URL_9213 + "inspectionReport";

    //护理列表
    public static String HL_LIST = BASE_URL_SePropertyManagement + "book/nursingPlanRecord";

    //健康监测
    public static String Health_Card = BASE_URL_9213 + "oxygen/lastPhysicalExamination/";

}
