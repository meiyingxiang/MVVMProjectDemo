package com.example.mvvmprojectdemo.taobao.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvmprojectdemo.R
import com.example.mvvmprojectdemo.taobao.bean.MapData
import kotlinx.android.synthetic.main.item_on_sell_layouyt.view.*

class OnSellListAdapter : RecyclerView.Adapter<OnSellListAdapter.InnerHolder>() {

    private var mContentListData = arrayListOf<MapData>()

    /**
     * 内部类
     */
    class InnerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerHolder {
        val inflate = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_on_sell_layouyt, parent, false)
        return InnerHolder(inflate)
    }

    override fun getItemCount(): Int {
        return mContentListData.size
    }

    override fun onBindViewHolder(holder: InnerHolder, position: Int) {
        holder.itemView.apply {
            with(mContentListData[position]) {
                itemOnSellTitleTv.text = title
                itemPriceTv.text = String.format("¥%.2f", zk_final_price.toFloat() - coupon_amount)
                val itemImgUrl = "https:$pict_url"
                Glide.with(context)
                    .load(itemImgUrl)
                    .into(itemPictureImg)
            }
        }

        holder.itemView.run {
            setOnClickListener {
                Toast.makeText(context, "点击了第$position 条item", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun setListData(it: List<MapData>) {
        mContentListData.clear()
        mContentListData.addAll(it)
        notifyDataSetChanged()
    }
}