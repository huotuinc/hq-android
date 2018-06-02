package com.huotu.android.couponsleague.widget

import android.content.Context
import android.net.Uri
import android.view.ViewGroup
import android.widget.ImageView
import com.facebook.drawee.view.SimpleDraweeView
import com.youth.banner.loader.ImageLoader

class FrescoImageLoader() :ImageLoader(){

    override fun createImageView(context: Context?): ImageView {
        var simpleDraweeView = SimpleDraweeView(context)
        //simpleDraweeView.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)
        return simpleDraweeView
        //return super.createImageView(context)
    }

    override fun displayImage(context: Context?, path: Any?, imageView: ImageView?) {
        var uri = Uri.parse(path.toString())
        imageView!!.setImageURI(uri)
    }
}