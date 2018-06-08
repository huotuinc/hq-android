package com.huotu.android.couponsleague.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.huotu.android.couponsleague.R
import com.huotu.android.couponsleague.bean.OrderBean

class OrderAdapter(data:ArrayList<OrderBean>)
    :BaseQuickAdapter<OrderBean,BaseViewHolder>(R.layout.layout_order_item ,data) {

    override fun convert(helper: BaseViewHolder?, item: OrderBean?) {

    }
}