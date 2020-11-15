package com.example.mvvmprojectdemo.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mvvmprojectdemo.lifecycle.ILifecycle
import com.example.mvvmprojectdemo.lifecycle.LifeState
import com.example.mvvmprojectdemo.lifecycle.LifecycleProvider

open class BaseActivity : AppCompatActivity() {

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifeCycleListener.forEach {
            it.onCreate()
        }
    }

    override fun onStart() {
        super.onStart()
        lifeCycleListener.forEach {
            it.onStart()
        }
    }

    override fun onResume() {
        super.onResume()
        lifeCycleListener.forEach {
            it.onResume()
        }
    }

    override fun onPause() {
        super.onPause()
        lifeCycleListener.forEach {
            it.onPause()
        }
    }

    override fun onStop() {
        super.onStop()
        lifeCycleListener.forEach {
            it.onStop()
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        lifeCycleListener.forEach {
            it.onDestroy()
        }
    }

}