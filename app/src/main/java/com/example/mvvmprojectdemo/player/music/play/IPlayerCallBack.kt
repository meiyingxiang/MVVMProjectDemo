package com.example.mvvmprojectdemo.player.music.play

interface IPlayerCallBack {

    //播放歌曲名称
    fun onTitleChange(title: String)

    //播放进度
    fun onProgressChange(current: Int)

    //播放
    fun onPlaying()

    //暂停
    fun onPlayerPause()

    //封面
    fun onCoverChange(cover: String)


}