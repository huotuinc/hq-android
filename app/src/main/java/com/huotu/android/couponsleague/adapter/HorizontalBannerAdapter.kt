package com.huotu.android.couponsleague.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.facebook.drawee.view.SimpleDraweeView
import com.huotu.android.couponsleague.R

class HorizontalBannerAdapter(data:List<String>)
    :BaseQuickAdapter<String,BaseViewHolder>( R.layout.layout_horizontal_banner_item, data) {

    override fun convert(helper: BaseViewHolder?, item: String?) {
        helper!!.getView<SimpleDraweeView>(R.id.horizontal_banner_item_image).setImageURI(item)
    }
}