package com.huotu.android.couponsleague.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.facebook.drawee.view.SimpleDraweeView
import com.huotu.android.couponsleague.R
import com.huotu.android.couponsleague.utils.FrescoDraweeController
import com.huotu.android.couponsleague.utils.FrescoDraweeListener

class ImageAdaper(var width : Int , data :ArrayList<String>)
    :BaseQuickAdapter<String,BaseViewHolder>( R.layout.layout_quan_image_item , data)
    ,FrescoDraweeListener.ImageCallback{

    override fun convert(helper: BaseViewHolder?, item: String?) {

        var iv = helper!!.getView<SimpleDraweeView>(R.id.quan_image_item_icon )
        FrescoDraweeController.loadImage(iv ,width , 0 , item!! ,this )

    }

    override fun imageCallback(width: Int, height: Int, simpleDraweeView: SimpleDraweeView?) {
        simpleDraweeView!!.layoutParams.width=width
        simpleDraweeView!!.layoutParams.height=height
    }
}