package com.example.mvvmprojectdemo.player.music

import android.os.Looper
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import com.example.mvvmprojectdemo.App
import com.example.mvvmprojectdemo.lifecycle.*

/**
 * 数据容器
 * 可以监听数据的变化
 */
class DataListenerContainer2<T> {

    private var blocks = arrayListOf<(T?) -> Unit>()
    private var viewLifecycleProviders = hashMapOf<(T?) -> Unit, Lifecycle>()

    public var value: T? = null
        //当数据变化的时候通知更新
        set(value: T?) {
            if (Looper.getMainLooper().thread == Thread.currentThread()) {
                //判断对应View的生命周期是什么
                blocks.forEach {
                    dispatchValue(it, value)
                }
            } else {
                App.handler.post {
                    blocks.forEach {
//                        it.invoke(value)
                        dispatchValue(it, value)
                    }
                }
            }
            field = value
        }

    private fun dispatchValue(it: (T?) -> Unit, value: T?) {
        val lifecycleProvider = viewLifecycleProviders[it]
        if (lifecycleProvider != null &&
            lifecycleProvider.currentState.isAtLeast(Lifecycle.State.STARTED)
        ) {
            it.invoke(value)
        } else {
            Log.e("Frank", "不更新UI: ");
        }
    }


    fun addListener(block: (T?) -> Unit) {
        if (!blocks.contains { block }) {
            blocks.add(block)
        }
    }

    /**
     * 有可能有多个View进行监听
     * 所有owner->block
     * 管理起来
     */
    fun addListener(owner: LifecycleOwner, valueObserver: (T?) -> Unit) {
        val lifecycleProvider = owner.lifecycle
        viewLifecycleProviders[valueObserver] = lifecycleProvider

        val observerWrapper = ValueObserverWrapper(valueObserver)


        lifecycleProvider.addObserver(observerWrapper)
//        lifecycleProvider.addLifeCycleListener(observerWrapper)

        //当View销毁的时候要从集合中删除
        if (!blocks.contains { valueObserver }) {
            blocks.add(valueObserver)
        }
    }

    inner class ValueObserverWrapper(
        private val valueObserver: (T?) -> Unit
    ) : LifecycleObserver {

        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        fun removeValueObserver() {
            Log.e("Frank", "移除 removeValueObserver: " + Lifecycle.Event.ON_DESTROY);
            viewLifecycleProviders.remove(valueObserver)
        }

    }
}