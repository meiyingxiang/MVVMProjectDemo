package com.example.mvvmprojectdemo.taobao.api

import com.example.mvvmprojectdemo.taobao.bean.OnSellData
import com.example.mvvmprojectdemo.taobao.bean.ResultData
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    companion object {
        const val BASE_URL = "https://api.sunofbeach.net/shop/"
    }

    @GET("/onSell/{page}")
    suspend fun getOnSellList(@Path("page") path: Int): ResultData<OnSellData>


}