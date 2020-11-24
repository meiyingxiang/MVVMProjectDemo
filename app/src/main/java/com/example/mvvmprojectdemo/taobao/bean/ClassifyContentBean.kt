package com.example.mvvmprojectdemo.taobao.bean

/**
 * 分类详情的数据模型
 */
data class ClassifyContentBean(
    val category_id: Int,
    val category_name: Any,
    val click_url: String,
    val commission_rate: String,
    val coupon_amount: Int,
    val coupon_click_url: String,
    val coupon_end_time: Any,
    val coupon_info: Any,
    val coupon_remain_count: Int,
    val coupon_share_url: String,
    val coupon_start_fee: String,
    val coupon_start_time: Any,
    val coupon_total_count: Int,
    val item_description: String,
    val item_id: Long,
    val level_one_category_id: Int,
    val level_one_category_name: Any,
    val nick: Any,
    val pict_url: String,
    val seller_id: Int,
    val shop_title: Any,
    val small_images: Any,
    val title: String,
    val user_type: Int,
    val volume: Int,
    val zk_final_price: String
)