package com.yjkj.service_recoder.library.http

import com.yjkj.service_recoder.java.entity.ApprovalAgencyCountEntity
import com.yjkj.service_recoder.java.entity.AreaData
import com.yjkj.service_recoder.java.entity.EmployeesNumberEntity
import com.yjkj.service_recoder.java.entity.HomePageDataEntity
import com.yjkj.service_recoder.java.entity.HouseAnalysisEntity
import com.yjkj.service_recoder.java.entity.LoginEntity
import com.yjkj.service_recoder.java.entity.OwnerEntity
import com.yjkj.service_recoder.java.entity.ServingNumberEntity
import com.yjkj.service_recoder.java.entity.UserInfoStatusEntity
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiService {


    @POST
    suspend fun login(@Url path: String = ApiContents.login, @Body body: RequestBody):DataResult<LoginEntity>

    @GET
    suspend fun requestAreaData(@Url path : String = ApiContents.requestArea):DataResult<AreaData>

    /**
     * 请求住户分析图
     */
    @GET
    suspend fun requestHouseholdAnalysis(@Url path: String = ApiContents.householdAnalysis):DataResult<HouseAnalysisEntity>

    /**
     * 请求主页数据
     */
    @GET
    suspend fun requestHomePageData(@Url path: String = ApiContents.getHomePageData):DataResult<HomePageDataEntity>


    /**
     * 上班人数
     */
    @GET
    suspend fun employeesNumber(@Url path: String = ApiContents.employeesNumber):DataResult<EmployeesNumberEntity>

    /**
     * 服务人数
     */
    @GET
    suspend fun servingNumber(@Url path: String = ApiContents.servingNumber):DataResult<ServingNumberEntity>

    /**
     * 今日物业工单
     */
    @GET
    suspend fun todayServiceBook(@Url path: String = ApiContents.todayServiceBook):DataResult<Int>

    /**
     * 今日商城订单
     */
    @GET
    suspend fun todayGoodsOrder(@Url path: String = ApiContents.todayGoodsOrder):DataResult<Int>

    /**
     * 适老订单
     */
    @GET
    suspend fun todayOldBook(@Url path: String = ApiContents.todayOldBook):DataResult<Int>

    /**
     * 餐饮订单
     */
    @GET
    suspend fun todayRestaurantOrder(@Url path: String = ApiContents.todayRestaurantOrder):DataResult<Int>

    /**
     * 审批代办数量
     */
    @GET
    suspend fun approvalAgencyCount(@Url path: String = ApiContents.approvalAgencyCount):DataResult<ApprovalAgencyCountEntity>

    /**
     * 到期合同数量
     */
    @GET
    suspend fun searchExpireNumber(@Url path: String = ApiContents.searchExpireNumber):DataResult<Int>

    /**
     * 点击充值 查询接口
     */
    @GET
    suspend fun ownerList(@Url path: String = ApiContents.ownerList, @Query("pageNum") pageNum : Int, @Query("pageSize") pageSize : Int = 20,
                          @Query("ownerName") ownerName : String = "",
                          @Query("ownerCommunity") ownerCommunity : String = "",
                          @Query("ownerBuilding") ownerBuilding : String = ""):DataResult<MutableList<OwnerEntity>>

    /**
     * 请求超过60业主
     */
    @GET
    suspend fun over60OwnerList(@Url path: String = ApiContents.ownerList, @Query("minAge") minAge : Int = 60, @Query("pageNum") pageNum : Int, @Query("pageSize") pageSize : Int = 20,
                                @Query("ownerName") ownerName : String = "",
                                @Query("ownerCommunity") ownerCommunity : String = "",
                                @Query("ownerBuilding") ownerBuilding : String = ""):DataResult<MutableList<OwnerEntity>>

    /**
     * 独居老人
     */
    @GET
    suspend fun ownerLivingOwnerList(@Url path: String = ApiContents.ownerList, @Query("ownerLiving") ownerLiving : String = "00", @Query("pageNum") pageNum : Int, @Query("pageSize") pageSize : Int = 20,
                                     @Query("ownerName") ownerName : String = "",
                                     @Query("ownerCommunity") ownerCommunity : String = "",
                                     @Query("ownerBuilding") ownerBuilding : String = ""):DataResult<MutableList<OwnerEntity>>

    /**
     * 请求上班人数列表
     */
    @GET
    suspend fun workList(@Url path: String = ApiContents.workList, @Query("workerCheckStatus") workerCheckStatus : Int = 2, @Query("pageNum") pageNum : Int, @Query("pageSize") pageSize : Int = 20):DataResult<MutableList<OwnerEntity>>

    /**
     * 正在服务中人数
     */
    @GET
    suspend fun serviceList(@Url path: String = ApiContents.serviceList, @Query("pageNum") pageNum : Int, @Query("pageSize") pageSize : Int = 20):DataResult<MutableList<OwnerEntity>>

    /**
     * 睡眠垫报警数量
     */
    @GET
    suspend fun warningNUm(@Url path: String = ApiContents.bedWarningNum):DataResult<Int>

    /**
     * 睡眠垫报警列表
     */
    @GET
    suspend fun bedWarningList(@Url path: String = ApiContents.bedWarningList, @Query("pageNum") pageNum : Int, @Query("pageSize") pageSize : Int = 20):DataResult<Any>

    /**
     * 物业工单list
     */
    @GET
    suspend fun todayServiceBookList(@Url path: String = ApiContents.todayServiceBookList, @Query("pageNum") pageNum : Int, @Query("pageSize") pageSize : Int = 20):DataResult<Any>
    /**
     * 适老订单list
     */
    @GET
    suspend fun todayOldBookList(@Url path: String = ApiContents.todayOldBookList, @Query("pageNum") pageNum : Int, @Query("pageSize") pageSize : Int = 20):DataResult<Any>

    /**
     * 商城订单list
     */
    @GET
    suspend fun todayGoodsOrderList(@Url path: String = ApiContents.todayGoodsOrderList, @Query("pageNum") pageNum : Int, @Query("pageSize") pageSize : Int = 20):DataResult<Any>

    /**
     * 餐饮订单list
     */
    @GET
    suspend fun todayRestaurantOrderList(@Url path: String = ApiContents.todayRestaurantOrderList, @Query("pageNum") pageNum : Int, @Query("pageSize") pageSize : Int = 20):DataResult<Any>

    /**
     * 第二个页面请求接口
     */
    @GET
    suspend fun secondPageOwnerList(@Url path: String = ApiContents.secondPageOwnerList,
                                    @Query("province") province : String = "",
                                    @Query("city") city : String = "",
                                    @Query("area") area : String = "",
                                    @Query("ownerName") ownerName : String = "",
                                    @Query("ownerCommunity") ownerCommunity : String = "",
                                    @Query("ownerBuilding") ownerBuilding : String = "",
                                    @Query("ownerUnit") ownerUnit :String = "",
                                    @Query("ownerFloor") ownerFloor : String = "",
                                    @Query("ownerRoomNum") ownerRoomNum :String="",
                                    @Query("pageNum") pageNum : Int = 1,
                                    @Query("pageSize") pageSize : Int = 100):DataResult<MutableList<OwnerEntity>>

    /**
     * 睡眠垫报警数量
     */
    @GET
    suspend fun userInfoStatus(@Url path: String = ApiContents.UserInfoStatus):DataResult<UserInfoStatusEntity>

}