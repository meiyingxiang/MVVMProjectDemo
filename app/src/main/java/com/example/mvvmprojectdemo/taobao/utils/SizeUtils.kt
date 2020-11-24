package com.example.mvvmprojectdemo.taobao.utils

import android.content.Context

/**
 *静态类
 */
object SizeUtils {

    fun dip2px(context: Context, dpValue: Float): Int {
        val density = context.resources.displayMetrics.density
        return (dpValue * density + 0.5f).toInt()
    }

}