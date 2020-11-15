package com.example.mvvmprojectdemo.player

import com.example.mvvmprojectdemo.player.domain.Music

class PlayerModel {
    fun getMusicById(name: String): Music {
        return Music(
            "歌曲名： $name",
            "珊瑚海",
            "http://www.baidu.com"
        )
    }

}