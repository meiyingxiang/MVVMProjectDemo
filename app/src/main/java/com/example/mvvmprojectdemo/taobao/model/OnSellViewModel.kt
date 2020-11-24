package com.example.mvvmprojectdemo.taobao.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmprojectdemo.taobao.OnSellRepository
import com.example.mvvmprojectdemo.taobao.base.LoadState
import com.example.mvvmprojectdemo.taobao.bean.MapData
import com.example.mvvmprojectdemo.taobao.bean.OnSellData
import kotlinx.coroutines.launch

class OnSellViewModel : ViewModel() {

    private val onSellRepository by lazy {
        OnSellRepository()
    }

    val contentList = MutableLiveData<MutableList<MapData>>()

    val loadState = MutableLiveData<LoadState>()

    private var isLoadMore = false

    companion object {
        //默认第一页
        const val DEFAULT_PAGE = 1
    }

    private var mCurrentPage = DEFAULT_PAGE

    /**
     * 加载更多内容
     */
    fun loadMore() {
        isLoadMore = true
        loadState.value = LoadState.LOADING_MORE_LOADING
        mCurrentPage++
        this.listContentByPage(mCurrentPage)
    }

    /**
     * 加载首页内容
     */
    fun loadContent() {
        isLoadMore = false
        loadState.value = LoadState.LOADING
        this.listContentByPage(mCurrentPage)
    }

    /**
     * 加载页码
     */
    private fun listContentByPage(page: Int) {

        viewModelScope.launch {
            try {
                val onSellList: OnSellData = onSellRepository.getOnSellList(page)
                val oldValue = contentList.value ?: mutableListOf()
                oldValue.addAll(onSellList.tbk_dg_optimus_material_response.result_list.map_data)

                contentList.value = oldValue


                if (onSellList.tbk_dg_optimus_material_response.result_list.map_data.isEmpty()) {
                    loadState.value = if (isLoadMore) {
                        LoadState.LOADING_MORE_EMPTY
                    } else {
                        LoadState.EMPTY
                    }
                } else {
                    loadState.value = LoadState.SUCCESS
                }
            } catch (e: Exception) {
                Log.e("Frank", "异常 : $e");
                mCurrentPage--
                if (e is NullPointerException) {
                    // 空指针异常
                    loadState.value = LoadState.LOADING_MORE_EMPTY
                } else {
                    loadState.value = if (isLoadMore) {
                        LoadState.LOADING_MORE_ERROR
                    } else {
                        LoadState.ERROR
                    }

                }
            }
        }
    }


}