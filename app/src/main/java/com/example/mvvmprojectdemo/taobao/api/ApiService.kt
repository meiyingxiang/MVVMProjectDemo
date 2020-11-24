package com.example.mvvmprojectdemo.taobao.api

import com.example.mvvmprojectdemo.taobao.bean.ClassifyContentBean
import com.example.mvvmprojectdemo.taobao.bean.OnSellData
import com.example.mvvmprojectdemo.taobao.bean.ResultData
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    companion object {
        const val BASE_URL = "https://api.sunofbeach.net/shop/"
    }

    @GET("onSell/{page}")
    suspend fun getOnSellList(@Path("page") page: Int): ResultData<OnSellData>

    @GET("discovery/{materialId}/{page}")
    suspend fun getClassifyContentList(@Path("page") page: Int): ResultData<ClassifyContentBean>

}