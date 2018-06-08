package com.huotu.android.couponsleague.widget

import android.content.Context
import android.net.Uri
import android.support.constraint.ConstraintLayout
import android.view.ViewGroup
import android.widget.ImageView
import com.facebook.drawee.view.SimpleDraweeView
import com.huotu.android.couponsleague.utils.FrescoDraweeController
import com.huotu.android.couponsleague.utils.FrescoDraweeListener
import com.youth.banner.Banner
import com.youth.banner.loader.ImageLoader

class FrescoImageLoader(var banner : Banner, var width :Int ) :ImageLoader(), FrescoDraweeListener.ImageCallback{

    override fun createImageView(context: Context?): ImageView {
        var simpleDraweeView = SimpleDraweeView(context)
        //simpleDraweeView.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)
        return simpleDraweeView
        //return super.createImageView(context)
    }

    override fun displayImage(context: Context?, path: Any?, imageView: ImageView?) {
        var uri = Uri.parse(path.toString())
        //imageView!!.setImageURI(uri)
        FrescoDraweeController.loadImage(imageView as SimpleDraweeView , width , 0 , path.toString() , this)
    }

    override fun imageCallback(width: Int, height: Int, simpleDraweeView: SimpleDraweeView?) {
        if(simpleDraweeView==null) return
        var layoutParams = simpleDraweeView.layoutParams
        layoutParams.width = width
        layoutParams.height = height
        simpleDraweeView.layoutParams =layoutParams

        var layout2  = banner.layoutParams
        layout2.height=height
        banner.layoutParams=layout2
    }
}