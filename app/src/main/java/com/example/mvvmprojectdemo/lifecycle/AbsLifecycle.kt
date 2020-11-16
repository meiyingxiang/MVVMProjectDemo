package com.example.mvvmprojectdemo.lifecycle

abstract class AbsLifecycle : ILifecycle {

    open fun onCreate() {}

    open fun onStart() {}

    open fun onResume() {}

    open fun onPause() {}

    open fun onStop() {}

    open fun onDestroy() {}


}