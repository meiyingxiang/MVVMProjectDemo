package com.example.mvvmprojectdemo.player.music.list

import com.example.mvvmprojectdemo.player.domain.Music

class MusicModel {

    fun loadMusicByPage(page: Int, size: Int, callBack: OnMusicLoadResult) {
        val result = arrayListOf<Music>()
        Thread {
            for (i: Int in (0 until size)) {
                result.add(
                    Music(
                        "音乐的名称：$i",
                        "封面 $i",
                        "链接 $i"
                    )
                )
            }
            //数据完成
            callBack.onSuccess(result)

        }.start()
    }

    interface OnMusicLoadResult {
        fun onSuccess(musics: List<Music>)
        fun onError(msg: String, code: Int)
    }

}