package com.example.mvvmprojectdemo.taobao.bean

import com.example.mvvmprojectdemo.taobao.api.ApiException

data class ResultData<T>(val success: Boolean, val code: Int, val message: String, var data: T) {

    companion object {
        const val CODE_SUCCESS = 10000
    }

    fun apiData(): T {
        //获取Data数据
        //如果成功的code就返回数据,不成功返回异常
        if (CODE_SUCCESS == code) {
            return data
        } else {
            throw ApiException(code, message)
        }
    }
}