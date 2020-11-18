package com.example.mvvmprojectdemo.player.music.list

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.example.mvvmprojectdemo.lifecycle.AbsLifecycle
import com.example.mvvmprojectdemo.lifecycle.ILifecycle
import com.example.mvvmprojectdemo.lifecycle.ILifecycleOwner
import com.example.mvvmprojectdemo.lifecycle.LifeState
import com.example.mvvmprojectdemo.player.DataListenerContainer
import com.example.mvvmprojectdemo.player.domain.Music
import com.example.mvvmprojectdemo.player.music.DataListenerContainer2

class MusicPresenter4(owner: LifecycleOwner) : MusicModel.OnMusicLoadResult {

    private val viewLifecycleImpl by lazy {
        ViewLifecycleImpl()
    }

    init {
        owner.lifecycle.addObserver(viewLifecycleImpl)
    }

    private val musicModel by lazy {
        MusicModel()
    }

    enum class MusicLoadState {
        LOADING, EMPTY, SUCCESS, ERROR
    }

    val musicList = DataListenerContainer2<List<Music>>()

    val loadState = DataListenerContainer2<MusicLoadState>()

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

    @Suppress("UNREACHABLE_CODE")
    inner class ViewLifecycleImpl : LifecycleEventObserver {
        override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
            when (event) {
                Lifecycle.Event.ON_START -> {
                    Log.e("Frank", "开始监听网络变化: ");
                }
                Lifecycle.Event.ON_PAUSE -> {
                    Log.e("Frank", "停止监听网络变化: ");
                }
                else -> {

                }
            }
        }


    }
}