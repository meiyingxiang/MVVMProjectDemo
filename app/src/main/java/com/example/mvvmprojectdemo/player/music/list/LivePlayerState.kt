package com.example.mvvmprojectdemo.player.music.list

import androidx.lifecycle.LiveData

class LivePlayerState private constructor() : LiveData<MusicPresenter6.MusicLoadState>() {

    companion object{
        val instance by lazy {
            LivePlayerState()
        }
    }

}