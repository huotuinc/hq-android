package com.huotu.android.couponsleague.adapter

import android.support.constraint.ConstraintLayout
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.facebook.drawee.view.SimpleDraweeView
import com.huotu.android.couponsleague.R
import com.huotu.android.couponsleague.utils.DensityUtils
import com.huotu.android.couponsleague.utils.FrescoDraweeController
import com.huotu.android.couponsleague.utils.FrescoDraweeListener
import com.youth.banner.Banner

class HorizontalBannerAdapter(data:List<String>)
    :BaseQuickAdapter<String,BaseViewHolder>( R.layout.layout_horizontal_banner_item, data) , FrescoDraweeListener.ImageCallback {

    override fun convert(helper: BaseViewHolder?, item: String?) {
        var iv = helper!!.getView<SimpleDraweeView>(R.id.horizontal_banner_item_image)
        var width = DensityUtils.getScreenWidth(mContext)

        width = (width - width/6)/2

        FrescoDraweeController.loadImage(iv , width , 0 , item, this)
    }

    override fun imageCallback(width: Int, height: Int, simpleDraweeView: SimpleDraweeView?) {
        if(simpleDraweeView==null) return
        var layoutParams = simpleDraweeView.layoutParams
        layoutParams.width = width
        layoutParams.height = height
        simpleDraweeView.layoutParams =layoutParams
    }
}