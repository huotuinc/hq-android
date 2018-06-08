package com.huotu.android.couponsleague.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.huotu.android.couponsleague.R
import com.huotu.android.couponsleague.bean.MoneyBean
import java.util.*

class MoneyAdapter(data:ArrayList<MoneyBean>) :BaseQuickAdapter<MoneyBean,BaseViewHolder>( R.layout.layout_money_item , data) {

    override fun convert(helper: BaseViewHolder?, item: MoneyBean ?) {

    }
}