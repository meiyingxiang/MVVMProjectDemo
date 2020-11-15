package com.example.mvvmprojectdemo.player

import android.os.Looper
import com.example.mvvmprojectdemo.App

/**
 * 数据容器
 * 可以监听数据的变化
 */
class DataListenerContainer<T> {

    private var blocks = arrayListOf<(T?) -> Unit>()
    public var value: T? = null
        //当数据变化的时候通知更新
        set(value: T?) {
            if (Looper.getMainLooper().thread == Thread.currentThread()) {
                blocks.forEach {
                    it.invoke(value)
                }
            } else {
                App.handler.post {
                    blocks.forEach {
                        it.invoke(value)
                    }
                }
            }
            field = value
        }


    fun addListener(block: (T?) -> Unit) {
        if (!blocks.contains { block }) {
            blocks.add(block)
        }
    }
}