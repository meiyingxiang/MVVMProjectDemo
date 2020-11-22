package com.example.mvvmprojectdemo.taobao.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmprojectdemo.taobao.OnSellRepository
import com.example.mvvmprojectdemo.taobao.bean.OnSellData
import kotlinx.coroutines.launch

class OnSellViewModel : ViewModel() {

    private val onSellRepository by lazy {
        OnSellRepository()
    }

    companion object {
        //默认第一页
        const val DEFAULT_PAGE = 1
    }

    private var mCurrentPage = DEFAULT_PAGE

    /**
     * 加载更多内容
     */
    fun loadMore() {

    }

    /**
     * 加载首页内容
     */
    fun loadContent() {
        this.listContentByPage(mCurrentPage)
    }

    /**
     * 加载页码
     */
    private fun listContentByPage(page: Int) {
        viewModelScope.launch {
            try {
                val onSellList: OnSellData = onSellRepository.getOnSellList(page)
                Log.e("Frank", "listContentByPage : 加载页码 $page --->data =${onSellList.tbk_dg_optimus_material_response.result_list.map_data.size}");
            } catch (e: Exception) {
                Log.e("Frank", "异常 : $e");
            }
        }
    }

    val contentList = MutableLiveData<MutableList<String>>()

}