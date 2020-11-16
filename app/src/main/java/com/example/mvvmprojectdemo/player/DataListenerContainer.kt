package com.example.mvvmprojectdemo.player

import android.os.Looper
import com.example.mvvmprojectdemo.App
import com.example.mvvmprojectdemo.lifecycle.*

/**
 * 数据容器
 * 可以监听数据的变化
 */
class DataListenerContainer<T> {

    private var blocks = arrayListOf<(T?) -> Unit>()
    private var viewLifecycleProviders = hashMapOf<(T?) -> Unit, LifecycleProvider>()

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
            lifecycleProvider.isAtLeast(LifeState.STATE)
        ) {
            it.invoke(value)
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
    fun addListener(owner: ILifecycleOwner, valueObserver: (T?) -> Unit) {
        val lifecycleProvider = owner.lifecycleProvider()
        viewLifecycleProviders[valueObserver] = lifecycleProvider

        val observerWrapper = ValueObserverWrapper(valueObserver)

        lifecycleProvider.addLifeCycleListener(observerWrapper)

        //当View销毁的时候要从集合中删除
        if (!blocks.contains { valueObserver }) {
            blocks.add(valueObserver)
        }
    }

    inner class ValueObserverWrapper(
        private val valueObserver: (T?) -> Unit
    ) : AbsLifecycle() {
        override fun onViewLifecycleChange(state: LifeState) {
            //当监听到当前View，生命周期为Destroy时，就将lifecycleProvider从集合删除
            if (state == LifeState.DESTROY) {
                viewLifecycleProviders.remove(valueObserver)
            }
        }


    }
}