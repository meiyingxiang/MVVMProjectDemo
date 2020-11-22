package com.example.mvvmprojectdemo.taobao

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmprojectdemo.taobao.model.OnSellViewModel

class OnSellActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewMode = ViewModelProvider(this).get(OnSellViewModel::class.java)

        viewMode.loadContent()
    }


}