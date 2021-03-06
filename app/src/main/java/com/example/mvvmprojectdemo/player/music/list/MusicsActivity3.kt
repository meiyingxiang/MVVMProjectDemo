package com.example.mvvmprojectdemo.player.music.list

import android.os.Bundle
import android.util.Log
import com.example.mvvmprojectdemo.R
import com.example.mvvmprojectdemo.base.BaseActivity3
import kotlinx.android.synthetic.main.activity_music_layout.*

class MusicsActivity3 : BaseActivity3() {

    private val musicPresenter by lazy {
        MusicPresenter3(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music_layout)

        initDataListener()
        initListener()
    }


    /**
     * 监听数据变化
     */
    private fun initDataListener() {
        musicPresenter.musicList.addListener {
            //数据变化
            Log.e("Frank", "数据变化 : ${it?.size}");


            Log.e("Frank", "查看线程 : ${Thread.currentThread().name}");
        }

        musicPresenter.loadState.addListener {
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