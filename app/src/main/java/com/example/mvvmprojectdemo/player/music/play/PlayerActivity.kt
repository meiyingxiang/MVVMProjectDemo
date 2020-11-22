package com.example.mvvmprojectdemo.player.music.play

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mvvmprojectdemo.R
import kotlinx.android.synthetic.main.activity_player_layout.*
import org.jetbrains.anko.longToast


class PlayerActivity : AppCompatActivity(), IPlayerCallBack {

    private val playerPresenter by lazy {
        PlayerPresenter.instance
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_layout)
        playerPresenter.registerCallBack(this)
        initListener()
    }

    /**
     * 给控件设置点击事件
     */
    private fun initListener() {
        playerOrPauseBtn.setOnClickListener {
            //调用presenter层的暂停或者播放方法
            playerPresenter.doPlayOrPause()
        }

        playNextBtn.setOnClickListener {
            //下一首
            playerPresenter.playNext()
        }

        playPreviousBtn.setOnClickListener {
            playerPresenter.playPrevious()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        playerPresenter.unRegisterCallBack(this)
    }

    override fun onTitleChange(title: String) {
        songTitle.text = title
    }

    override fun onProgressChange(current: Int) {

    }

    override fun onPlaying() {
        //播放中，显示暂停
        playerOrPauseBtn.text = "暂停"
    }

    override fun onPlayerPause() {
        //暂停，显示播放
        playerOrPauseBtn.text = "播放"
    }

    override fun onCoverChange(cover: String) {
        //更换背景图
        longToast("更换封面 $cover").show()
    }

}