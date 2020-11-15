package com.example.mvvmprojectdemo.player.music.list

import android.util.Log
import com.example.mvvmprojectdemo.lifecycle.ILifecycle
import com.example.mvvmprojectdemo.lifecycle.ILifecycleOwner
import com.example.mvvmprojectdemo.player.DataListenerContainer
import com.example.mvvmprojectdemo.player.domain.Music

class MusicPresenter3(owner: ILifecycleOwner) : MusicModel.OnMusicLoadResult {

    private val viewLifecycleImpl by lazy {
        ViewLifecycleImpl()
    }

    init {
        owner.lifecycleProvider().addLifeCycleListener(viewLifecycleImpl)
    }

    private val musicModel by lazy {
        MusicModel()
    }

    enum class MusicLoadState {
        LOADING, EMPTY, SUCCESS, ERROR
    }

    val musicList = DataListenerContainer<List<Music>>()

    val loadState = DataListenerContainer<MusicLoadState>()

    val page: Int = 1
    val size: Int = 20

    /**
     * 获取音乐列表
     */
    fun getMusicsList() {
        loadState.value = MusicLoadState.LOADING
        //从model获取音乐列表数据
        musicModel.loadMusicByPage(page, size, this)
    }

    override fun onSuccess(musics: List<Music>) {
        if (musics.isEmpty()) {
            loadState.value = MusicLoadState.EMPTY
        } else {
            loadState.value = MusicLoadState.SUCCESS
            musicList.value = musics
        }
    }

    override fun onError(msg: String, code: Int) {
        loadState.value = MusicLoadState.ERROR
    }

    inner class ViewLifecycleImpl : ILifecycle {


        override fun onCreate() {

        }

        override fun onStart() {
            //开始监听网络变化
            Log.e("Frank", "开始监听网络变化 : ");
        }

        override fun onResume() {

        }

        override fun onPause() {

        }

        override fun onStop() {
            //停止监听网络变化
            Log.e("Frank", "结束监听网络变化 : ");
        }

        override fun onDestroy() {

        }
    }
}