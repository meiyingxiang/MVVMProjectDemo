package com.example.mvvmprojectdemo.player.music.list

import com.example.mvvmprojectdemo.base.BaseFragment

class MusicDetailFragment : BaseFragment() {

    private val musicPresenter by lazy {
        MusicPresenter()
    }

    init {
        lifecycleProvider.addLifeCycleListener(musicPresenter)
    }

}