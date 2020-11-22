package com.example.mvvmprojectdemo.taobao.api


/**
 * 异常
 */
data class ApiException(val code: Int, override val message: String?) : RuntimeException() {

}