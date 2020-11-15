package com.example.mvvmprojectdemo.player

import com.example.mvvmprojectdemo.player.domain.Music

/**
 * 播放音乐
 * 暂停音乐
 * 上一首
 * 下一首
 * ===========
 * 播放的状态
 * -通知ui改变播放状态
 * -进度
 * 上一首，下一首
 * -标题
 * -封面
 * 暂停音乐
 * 更新ui状态为暂停
 *
 * 数据驱动开发
 */
class PlayerPresenter2 private constructor() {

    private val playerModel by lazy {
        PlayerModel()
    }

    private val player by lazy {
        MusicPlayer()
    }

    var currentMusic = DataListenerContainer<Music>()

    var currentPlayState = DataListenerContainer<PlayerPresenter2.PlayState>()


    //使用懒加载，不会重复加载，需要才加载且是线程安全
    companion object {
        val instance by lazy {
            PlayerPresenter2()
        }
    }

    enum class PlayState {
        NONE, PLAYING, PAUSE, LOADING
    }


    /**
     * 根据状态控制播放状态
     */
    fun doPlayOrPause() {
        if (currentMusic.value == null) {
            //获取歌曲
            currentMusic.value = playerModel.getMusicById("周杰伦")
        }

        player.play(currentMusic.value)
        if (currentPlayState.value != PlayState.PLAYING) {
            //开始播放音乐
            //通知ui改变状态
            currentPlayState.value = PlayState.PLAYING
        } else {
            //暂停播放
            currentPlayState.value = PlayState.PAUSE
        }

    }

    /**
     * 播放下一首
     */
    fun playNext() {
        currentMusic.value = playerModel.getMusicById("下一首:晴天")

        //拿到下一首     ->变更UI ，标题和封面
        //设置给播放器
        //等待播放的回调通知
        currentPlayState.value = PlayState.PLAYING
    }

    /**
     * 播放上一首
     */
    fun playPrevious() {
        currentMusic.value = playerModel.getMusicById("上一首:告白气球")
        currentPlayState.value = PlayState.PLAYING
    }
}