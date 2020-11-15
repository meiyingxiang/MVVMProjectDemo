package com.example.mvvmprojectdemo.lifecycle

import com.example.mvvmprojectdemo.lifecycle.ILifecycle

/**
 * 管理所注册进来的接口，这个接口就是ILifeCycle
 * 保存当前View的生命周期状态
 */
open class LifecycleProvider {

    private var currentLifeState: LifeState? = null

    private val lifeCycleListener = arrayListOf<ILifecycle>()

    fun addLifeCycleListener(iLifecycle: ILifecycle) {
        if (!lifeCycleListener.contains(iLifecycle)) {
            lifeCycleListener.add(iLifecycle)
        }
    }

    fun remoteLifeCycleListener(iLifecycle: ILifecycle) {
        if (lifeCycleListener.contains(iLifecycle)) {
            lifeCycleListener.remove(iLifecycle)
        }
    }

    fun makeLifeState(state: LifeState) {
        currentLifeState = state
        when (state) {
            LifeState.CREATE -> {
                dispatchCreateState()
            }
            LifeState.STATE -> {
                dispatchStart()
            }
            LifeState.RESUME -> {
                dispatchResume()
            }
            LifeState.PAUSE -> {
                dispatchPause()
            }
            LifeState.STOP -> {
                dispatchStop()
            }
            LifeState.DESTROY -> {
                dispatchDestroy()
            }
        }
    }

    private fun dispatchDestroy() {
        lifeCycleListener.forEach { it.onDestroy() }
        //销毁的时候将数据清理掉
        lifeCycleListener.clear()
        //或者使用
//        lifeCycleListener.forEach { remoteLifeCycleListener(it) }
    }

    private fun dispatchStop() {
        lifeCycleListener.forEach { it.onStop() }
    }

    private fun dispatchPause() {
        lifeCycleListener.forEach { it.onPause() }
    }

    private fun dispatchResume() {
        lifeCycleListener.forEach { it.onResume() }
    }

    private fun dispatchStart() {
        lifeCycleListener.forEach { it.onStart() }
    }

    private fun dispatchCreateState() {
        lifeCycleListener.forEach {
            it.onCreate()
        }
    }

}