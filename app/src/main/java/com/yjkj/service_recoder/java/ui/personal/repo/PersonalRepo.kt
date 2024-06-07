package com.yjkj.property_management.ui.page.homePageFragment.secondHomePage.repo

import com.yjkj.service_recoder.library.base.BaseRepository
import com.yjkj.service_recoder.library.http.ApiContents
import com.yjkj.service_recoder.library.http.ApiService
import com.yjkj.service_recoder.library.http.RetrofitManager

class PersonalRepo : BaseRepository() {


    suspend fun requestAreaData()=flowOf {
        RetrofitManager.getApiService(ApiService::class.java).requestAreaData()
    }

    suspend fun requestInfo(id : Int) = flowOf {
        ApiContents.UserInfoStatus = ApiContents.UserInfoStatus + id
        RetrofitManager.getApiService(ApiService::class.java).userInfoStatus()
    }

}