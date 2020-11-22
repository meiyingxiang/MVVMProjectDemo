package com.example.mvvmprojectdemo.player.music.list

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.example.mvvmprojectdemo.R
import com.example.mvvmprojectdemo.base.BaseActivity3
import com.example.mvvmprojectdemo.player.domain.Music
import kotlinx.android.synthetic.main.activity_music_layout.*

class MusicsActivity6 : BaseActivity3() {

    lateinit var mForeverObserve: ForeverObserve

    private val musicPresenter by lazy {
        MusicPresenter6(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music_layout)

        initDataListener()
        initListener()
    }

    inner class ForeverObserve : Observer<List<Music>> {
        override fun onChanged(t: List<Music>?) {
            Log.e("Frank", "ForeverObserve 里的数据列表: $t");
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        musicPresenter.liveMusicList.removeObserver(mForeverObserve)
    }

    /**
     * 监听数据变化
     */
    private fun initDataListener() {
        mForeverObserve = ForeverObserve()
        musicPresenter.liveMusicList.observeForever(mForeverObserve)

//        musicPresenter.liveMusicList.observe(
//            this,
//            Observer {
//                Log.e("Frank", "livedata 里的数据列表: $it");
//            }
//        )

        musicPresenter.musicList.addListener(this) {
            //数据变化
            Log.e("Frank", "数据变化 : ${it?.size}");


            Log.e("Frank", "查看线程 : ${Thread.currentThread().name}");
            textView.text = "当前加载的数据 ${it?.size}"
        }

        musicPresenter.loadState.addListener(this) {
            //数据状态
            Log.e("Frank", "数据状态 : $it");
        }
    }

    /**
     * 点击监听
     */
    private fun initListener() {
        getMusicBtn.setOnClickListener {
            musicPresenter.getMusicsList()
        }
    }

}