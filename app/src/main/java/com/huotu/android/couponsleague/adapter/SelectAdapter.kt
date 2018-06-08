package com.huotu.android.couponsleague.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.huotu.android.couponsleague.R
import com.huotu.android.couponsleague.bean.KeyValue

class SelectAdapter(data:ArrayList<KeyValue>) :BaseQuickAdapter<KeyValue,BaseViewHolder>( R.layout.layout_select_item , data) {

    override fun convert(helper: BaseViewHolder?, item: KeyValue?) {
        helper!!.setText(R.id.select_dialog_item_title, item!!.desc)
    }
}