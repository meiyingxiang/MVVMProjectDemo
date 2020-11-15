package com.example.mvvmprojectdemo.player

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mvvmprojectdemo.R
import kotlinx.android.synthetic.main.activity_float_player.*

class FloatPlayerControllerActivity : AppCompatActivity(), IPlayerCallBack {

    private val playerPresenter by lazy {
        PlayerPresenter.instance
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_float_player)
        playerPresenter.registerCallBack(this)
        initListener()
    }

    private fun initListener() {
        playOrPauseBtn.setOnClickListener {
            playerPresenter.doPlayOrPause()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        playerPresenter.unRegisterCallBack(this)
    }

    override fun onTitleChange(title: String) {

    }

    override fun onProgressChange(current: Int) {

    }

    /**
     * UI驱动开发
     */
    override fun onPlaying() {
        playOrPauseBtn.text = "暂停"
    }

    override fun onPlayerPause() {
        playOrPauseBtn.text = "播放"
    }

    override fun onCoverChange(cover: String) {

    }

}