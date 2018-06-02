package com.huotu.android.couponsleague.adapter

import android.support.constraint.ConstraintLayout
import android.support.design.widget.CoordinatorLayout
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.facebook.drawee.view.SimpleDraweeView
import com.huotu.android.couponsleague.R
import com.huotu.android.couponsleague.bean.DetailBean
import com.huotu.android.couponsleague.utils.DensityUtils
import com.huotu.android.couponsleague.utils.FrescoDraweeController
import com.huotu.android.couponsleague.utils.FrescoDraweeListener


class DetailAdapter(data :ArrayList<DetailBean>)
    :BaseQuickAdapter<DetailBean,BaseViewHolder>(R.layout.layout_detail_item ,  data) , FrescoDraweeListener.ImageCallback {



    override fun convert(helper: BaseViewHolder?, item: DetailBean?) {

       var view = helper!!.getView<SimpleDraweeView>(R.id.detail_item_pic)
       var  sw = DensityUtils.getScreenWidth(mContext)
        FrescoDraweeController.loadImage( view , sw , 0, item!!.url , this)
    }

    override fun imageCallback(width: Int, height: Int, simpleDraweeView: SimpleDraweeView?) {
        if(simpleDraweeView==null) return
        simpleDraweeView.layoutParams = ConstraintLayout.LayoutParams(width , height)
    }
}