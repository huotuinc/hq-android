package com.huotu.android.couponsleague.adapter

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.facebook.drawee.view.SimpleDraweeView
import com.huotu.android.couponsleague.R
import com.huotu.android.couponsleague.bean.InviteBean


class InviteAdapter :PagerAdapter() {
    private var data=ArrayList<InviteBean>()
    private var views = ArrayList<View>()

    fun setData(data:ArrayList<InviteBean>,context :Context ){
        this.data=data
        this.views.clear()

        var layoutInflater=LayoutInflater.from(context)

        for(i in 0 until data.size){
            var view = layoutInflater.inflate(R.layout.layout_invite_item,null)
            var iv = view.findViewById<SimpleDraweeView>(R.id.invite_item_image)
            iv.setImageURI(data[i].url)
            var iv2 = view.findViewById<ImageView>(R.id.invite_item_qrcode)
            iv2.setImageBitmap(data[i].bitmap)
            this.views.add(view)
        }
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        var v = views.get(position)
        if(null!= v.parent){
            (v.parent as ViewGroup).removeView(v)
        }

        container.addView(v)
        return v
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view.equals(`object`)
    }

    override fun getCount(): Int {
        return data.size
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        //super.destroyItem(container, position, `object`)
        container.removeView( views[position] )
    }
}