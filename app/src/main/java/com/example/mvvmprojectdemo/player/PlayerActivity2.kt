package com.example.mvvmprojectdemo.player

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mvvmprojectdemo.R
import kotlinx.android.synthetic.main.activity_player_layout.*
import org.jetbrains.anko.toast


class PlayerActivity2 : AppCompatActivity() {

    private val playerPresenter by lazy {
        PlayerPresenter2.instance
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_layout)
        initListener()
        initDataListener()
    }

    /**
     * 对数据进行监听，更新UI
     */
    private fun initDataListener() {
        playerPresenter.currentMusic.addListener {
            //数据变化，更新UI
            songTitle.text = it?.name
            toast("更换封面 ${it?.cover}").show()
        }
        playerPresenter.currentPlayState.addListener {
            //音乐状态监听
            playerOrPauseBtn.text = when (it) {
                PlayerPresenter2.PlayState.PAUSE -> {
                    "播放"
                }
                PlayerPresenter2.PlayState.PLAYING -> {
                    "暂停"
                }
                else -> "播放"
            }
        }

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
}