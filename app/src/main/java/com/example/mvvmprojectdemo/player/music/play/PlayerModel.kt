package com.example.mvvmprojectdemo.player.music.play

import com.example.mvvmprojectdemo.player.domain.Music

class PlayerModel {
    fun getMusicById(name: String): Music {
        return Music(
            "歌曲名： $name",
            "珊瑚海",
            "http://www.baidu.com"
        )
    }

    interface OnMusicLoadResult {
        fun onSuccess(musics: List<Music>)
        fun onError(msg: String, code: Int)
    }

}