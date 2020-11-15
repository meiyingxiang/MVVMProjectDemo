package com.example.mvvmprojectdemo.player

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
 * UI驱动开发
 */
class PlayerPresenter private constructor(){
    //使用懒加载，不会重复加载，需要才加载且是线程安全
    companion object{
        val instance by lazy {
            PlayerPresenter()
        }
    }

    enum class PlayState {
        NONE, PLAYING, PAUSE, LOADING
    }

    private val callBackList = arrayListOf<IPlayerCallBack>()

    private var currentPlayState = PlayState.NONE

    fun registerCallBack(callBack: IPlayerCallBack) {
        if (!callBackList.contains(callBack)) {
            callBackList.add(callBack)
        }
    }

    fun unRegisterCallBack(callBack: IPlayerCallBack) {
        callBackList.remove(callBack)
    }

    /**
     * 根据状态控制播放状态
     */
    fun doPlayOrPause() {
        dispatchTitleChange("当前播放歌曲标题")
        dispatchCaverChange("当前播放歌曲背景")
        if (currentPlayState != PlayState.PLAYING) {
            //开始播放音乐
            //通知ui改变状态
            dispatchPlayingState()
            currentPlayState = PlayState.PLAYING
        } else {
            //暂停播放
            dispatchPauseState()
            currentPlayState = PlayState.PAUSE
        }

    }

    private fun dispatchPauseState() {
        callBackList.forEach {
            it.onPlayerPause()
        }
    }

    private fun dispatchPlayingState() {
        callBackList.forEach {
            it.onPlaying()
        }
    }

    /**
     * 播放下一首
     */
    fun playNext() {
        //拿到下一首     ->变更UI ，标题和封面
        dispatchTitleChange("切换到下一首，标题变化...")
        dispatchCaverChange("下一首更换背景")
        //设置给播放器
        //等待播放的回调通知
        currentPlayState = PlayState.PLAYING
    }

    private fun dispatchCaverChange(cover: String) {
        callBackList.forEach {
            it.onCoverChange(cover)
        }
    }

    private fun dispatchTitleChange(title: String) {
        callBackList.forEach {
            it.onTitleChange(title)
        }
    }

    /**
     * 播放上一首
     */
    fun playPrevious() {
        dispatchTitleChange("切换到上一首，标题变化...")
        dispatchCaverChange("上一首更换背景")
        currentPlayState = PlayState.PLAYING
    }
}