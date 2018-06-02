package com.huotu.android.couponsleague.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.huotu.android.couponsleague.R


class DataAdapter(data: List<String>) :BaseQuickAdapter<String , BaseViewHolder>( R.layout.layout_goods_item_1 , data) {



    override fun convert(helper: BaseViewHolder?, item: String) {

    }
}