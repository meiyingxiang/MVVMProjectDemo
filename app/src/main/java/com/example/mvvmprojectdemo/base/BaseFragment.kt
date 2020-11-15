package com.example.mvvmprojectdemo.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.mvvmprojectdemo.lifecycle.LifeState
import com.example.mvvmprojectdemo.lifecycle.LifecycleProvider

open class BaseFragment : Fragment() {

    val lifecycleProvider by lazy {
        LifecycleProvider()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleProvider.makeLifeState(LifeState.CREATE)
    }

    override fun onStart() {
        super.onStart()
        lifecycleProvider.makeLifeState(LifeState.STATE)
    }

    override fun onResume() {
        super.onResume()
        lifecycleProvider.makeLifeState(LifeState.RESUME)
    }

    override fun onPause() {
        super.onPause()
        lifecycleProvider.makeLifeState(LifeState.PAUSE)
    }

    override fun onStop() {
        super.onStop()
        lifecycleProvider.makeLifeState(LifeState.STOP)
    }


    override fun onDestroy() {
        super.onDestroy()
        lifecycleProvider.makeLifeState(LifeState.DESTROY)
    }

}