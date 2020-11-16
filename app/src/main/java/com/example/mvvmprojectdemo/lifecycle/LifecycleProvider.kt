package com.example.mvvmprojectdemo.lifecycle

import android.util.Log
import com.example.mvvmprojectdemo.lifecycle.ILifecycle

/**
 * 管理所注册进来的接口，这个接口就是ILifeCycle
 * 保存当前View的生命周期状态
 */
open class LifecycleProvider {

    private var currentLifeState: LifeState = LifeState.DESTROY

    private val lifeCycleListener = arrayListOf<AbsLifecycle>()

    fun addLifeCycleListener(absLifecycle: AbsLifecycle) {
        if (!lifeCycleListener.contains(absLifecycle)) {
            lifeCycleListener.add(absLifecycle)
        }
    }

    fun remoteLifeCycleListener(absLifecycle: AbsLifecycle) {
        if (lifeCycleListener.contains(absLifecycle)) {
            lifeCycleListener.remove(absLifecycle)
        }
    }

    fun makeLifeState(state: LifeState) {
        currentLifeState = state
        lifeCycleListener.forEach {
            //通知改变状态
            it.onViewLifecycleChange(state)
        }
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

    fun isAtLeast(state: LifeState): Boolean {
        Log.e("Frank", "当前状态: $currentLifeState,运行状态 $state");
        return currentLifeState > state
    }

}