package com.example.mvvmprojectdemo.taobao

import com.example.mvvmprojectdemo.taobao.api.RetrofitClient

class OnSellRepository {

    suspend fun getOnSellList(page: Int) =
            RetrofitClient.apiService.getOnSellList(page).apiData()



}