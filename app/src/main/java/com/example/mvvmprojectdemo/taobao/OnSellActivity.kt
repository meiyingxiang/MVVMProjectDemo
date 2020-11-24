package com.example.mvvmprojectdemo.taobao

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmprojectdemo.R
import com.example.mvvmprojectdemo.taobao.adapter.OnSellListAdapter
import com.example.mvvmprojectdemo.taobao.base.LoadState
import com.example.mvvmprojectdemo.taobao.model.OnSellViewModel
import com.example.mvvmprojectdemo.taobao.utils.SizeUtils
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout
import kotlinx.android.synthetic.main.activity_onsell_layout.*
import kotlinx.android.synthetic.main.on_loading_error_layout.*
import org.jetbrains.anko.toast

class OnSellActivity : AppCompatActivity() {

    private val mViewModel by lazy {
        ViewModelProvider(this).get(OnSellViewModel::class.java)
    }

    private val onSellListAdapter by lazy {
        OnSellListAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onsell_layout)
        initView()
        initObserver()
    }

    /**
     * 观察数据变化
     */
    private fun initObserver() {
        mViewModel.apply {
            contentList.observe(this@OnSellActivity, Observer {
                //内容列表更新
                //更细适配器
                onSellListAdapter.setListData(it)
            })

            loadState.observe(this@OnSellActivity, Observer {
                //根据加载的状态来显示不同view
                when (it) {
                    LoadState.LOADING -> {
                        loadingView.visibility = View.VISIBLE
                        errorView.visibility = View.GONE
                        emptyView.visibility = View.GONE
                        contentRefreshLayout.visibility = View.GONE
                    }
                    LoadState.ERROR -> {
                        errorView.visibility = View.VISIBLE

                        loadingView.visibility = View.GONE
                        emptyView.visibility = View.GONE
                        contentRefreshLayout.visibility = View.GONE
                    }
                    LoadState.EMPTY -> {
                        emptyView.visibility = View.VISIBLE

                        loadingView.visibility = View.GONE
                        errorView.visibility = View.GONE
                        contentRefreshLayout.visibility = View.GONE
                    }
                    LoadState.SUCCESS -> {
                        contentRefreshLayout.visibility = View.VISIBLE
                        loadingView.visibility = View.GONE
                        errorView.visibility = View.GONE
                        emptyView.visibility = View.GONE
                        contentRefreshLayout.finishLoadmore()
                    }
                    LoadState.LOADING_MORE_ERROR -> {
                        Toast.makeText(this@OnSellActivity, "网络不佳，请稍后重试...", Toast.LENGTH_SHORT)
                            .show()
                        contentRefreshLayout.finishLoadmore()
                    }
                    LoadState.LOADING_MORE_EMPTY -> {
                        Toast.makeText(this@OnSellActivity, "已经加载全部内容...", Toast.LENGTH_SHORT)
                            .show()
                        contentRefreshLayout.finishLoadmore()
                    }
                    LoadState.LOADING_MORE_LOADING -> {

                    }
                }
            })
        }.loadContent()
    }

    /**
     * 初始化视图
     */
    private fun initView() {
        onSellRecycle.run {
            layoutManager = LinearLayoutManager(this@OnSellActivity)
            adapter = onSellListAdapter
            addItemDecoration(
                object : RecyclerView.ItemDecoration() {
                    override fun getItemOffsets(
                        outRect: Rect,
                        view: View,
                        parent: RecyclerView,
                        state: RecyclerView.State
                    ) {
                        outRect.apply {
                            val padding = SizeUtils.dip2px(this@OnSellActivity, 4.0f)
                            top = padding
                            left = padding
                            bottom = padding
                            right = padding
                        }
                    }
                }
            )
        }
        hideAllView()

        netWorkErrorTips.setOnClickListener {
            mViewModel.loadContent()
        }

        contentRefreshLayout.run {
            setEnableLoadmore(true)
            setEnableRefresh(false)
            setEnableOverScroll(true)
            setOnRefreshListener(object : RefreshListenerAdapter() {
                override fun onLoadMore(refreshLayout: TwinklingRefreshLayout?) {
                    super.onLoadMore(refreshLayout)
                    //加载更多
                    mViewModel.loadMore()
                }
            })
        }
    }

    private fun hideAllView() {
        contentRefreshLayout.visibility = View.GONE
        emptyView.visibility = View.GONE
        loadingView.visibility = View.GONE
        errorView.visibility = View.GONE
    }
}