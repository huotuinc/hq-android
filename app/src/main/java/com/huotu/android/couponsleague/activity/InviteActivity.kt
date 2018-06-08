package com.huotu.android.couponsleague.activity

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.os.Environment
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import com.facebook.cache.common.SimpleCacheKey
import com.facebook.drawee.backends.pipeline.Fresco
import com.huotu.android.couponsleague.R
import com.huotu.android.couponsleague.adapter.InviteAdapter
import com.huotu.android.couponsleague.base.BaseActivity
import com.huotu.android.couponsleague.bean.InviteBean
import com.huotu.android.couponsleague.mvp.IPresenter
import com.huotu.android.couponsleague.utils.DensityUtils
import com.huotu.android.couponsleague.utils.ImageUtils
import com.huotu.android.couponsleague.utils.QrCodeUtils
import com.huotu.android.couponsleague.utils.showToast
import com.huotu.android.couponsleague.widget.coverflow.CoverFlow
import kotlinx.android.synthetic.main.activity_invite.*
import kotlinx.android.synthetic.main.layout_header2.*
import java.io.File
import java.util.*

/**
 * 邀请好友
 */
class InviteActivity : BaseActivity<IPresenter>() , View.OnClickListener ,View.OnTouchListener {

    private var inviteAdapter:InviteAdapter?=null
    private var data=ArrayList<InviteBean>()
    private var gestureDetector:GestureDetector?=null
    private var bannerGestureListener : BannerGestureListener?=null
    private var width = 0
    private var height =0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_invite)

        header_title.text="邀请好友"
        header_left_image.setOnClickListener(this)
        header_right_text.text="分享规则"
        header_right_text.setOnClickListener(this)

        width = DensityUtils.dip2px(this , 80f)
        height =width

        data.clear()
        var url = "http://app.infunpw.com/commons/images/cinema/cinema_films/3823.jpg"
        var bitmap = QrCodeUtils.createQrCode(this , url , width ,height  )

        data.add( InviteBean( "http://app.infunpw.com/commons/images/cinema/cinema_films/3823.jpg",  bitmap ))
        url = "http://app.infunpw.com/commons/images/cinema/cinema_films/3566.jpg"
        bitmap = QrCodeUtils.createQrCode(this , url , width,height)
        data.add( InviteBean( url ,bitmap ) )
        url = "http://app.infunpw.com/commons/images/cinema/cinema_films/3757.jpg"
        bitmap = QrCodeUtils.createQrCode(this,url,width,height)
        data.add( InviteBean( url ,bitmap ))
        url = "http://app.infunpw.com/commons/images/cinema/cinema_films/3823.jpg"
        bitmap = QrCodeUtils.createQrCode(this,url,width,height)
        data.add( InviteBean( url,bitmap ))
        url ="http://t00img.yangkeduo.com/t09img/images/2018-05-28/4ac0853e1a7a898315f5155bdb733dff.jpeg"
        bitmap = QrCodeUtils.createQrCode(this,url,width,height)
        data.add( InviteBean( url,bitmap ))
        inviteAdapter= InviteAdapter()

        inviteAdapter!!.setData(data,this)
        invite_viewPager.offscreenPageLimit=3
        invite_viewPager.setOnTouchListener(this)
        invite_viewPager.pageMargin = DensityUtils.dip2px(this,30f)
        invite_viewPager.adapter=inviteAdapter

        CoverFlow.Builder()
                .with(invite_viewPager)
                .scale(0.3f)
                .pagerMargin(-30f)
                .spaceSize(0f)
                .build()


        bannerGestureListener = BannerGestureListener()
        gestureDetector = GestureDetector(this, bannerGestureListener)
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.header_left_image->{
                finish()
            }
            R.id.header_right_text->{
                showToast("share")

            }
        }
    }


    private fun mergeBitmapAndShare(bean : InviteBean){
        var fileBitmapResource = Fresco.getImagePipelineFactory()
                .mainFileCache.getResource( SimpleCacheKey(bean.url)  )
        var sourceBitmap = ImageUtils.getBitmap(fileBitmapResource.openStream())

        var sw = sourceBitmap.width
        var sh = sourceBitmap.height
        var w = bean.bitmap!!.width
        var h = bean.bitmap!!.height

        var x = 0
        var y = 0
        //x = if( x<0) 0 else x
        //y = if(y<0) 0 else y


//        if( sw < sh ){
//            sh= sw
//        }else{
//            sw = sh
//        }

        var qrw = sw/3
        var qrh = sh/3
        if( qrw >= w ){
            x = (sw- w )/2
            y = (sh- h )/2 + h
        }else{
            x = (sw- qrw)/2
            y = (sh - qrh)/2 + qrh
        }

        //var qrCodeBitmap = ImageUtils.scale(bean.bitmap , )

        var mergeBitmap = ImageUtils.addImageWatermark(sourceBitmap , bean.bitmap , x, y ,255)



        var filePath = getFilePath()+"/"+ UUID.randomUUID().toString()+".jpeg"
        ImageUtils.save(mergeBitmap , filePath , Bitmap.CompressFormat.JPEG)

    }

    private fun getFilePath():String{
        if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
            val path = Environment.getExternalStorageDirectory().toString() + "/coupons/images"
            val dir = File(path)
            if (!dir.exists()) {
                dir.mkdirs()
            }
            return path
        }

        return this.cacheDir.path
    }


    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
//        when (event!!.getAction()) {
//            MotionEvent.ACTION_DOWN -> {
//                //isScroll = true
//                //removeCallbacks(runnable)
//            }
//            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL, MotionEvent.ACTION_OUTSIDE -> {
//                //isScroll = false
//                //isFastScroll = true
//                /*
//                         * 左右滑动未翻页的情况下需要在此触发自动翻页
//                         */
//                //postDelayed(runnable, DELAY_MILLIS)
//            }
//        }
        gestureDetector!!.onTouchEvent(event)
        return false
    }

    private inner class BannerGestureListener : GestureDetector.SimpleOnGestureListener() {
        override fun onScroll(e1: MotionEvent, e2: MotionEvent, distanceX: Float, distanceY: Float): Boolean {
            return Math.abs(distanceY) < Math.abs(distanceX)
        }

        override fun onSingleTapUp(e: MotionEvent): Boolean {
            val currentPosition = invite_viewPager.currentItem
            var bean = data[currentPosition]
            showToast(bean.url)
            mergeBitmapAndShare(bean)
//            if (null != bannerItemClick)
//                bannerItemClick.onClick(adapter.getItem(currentPosition))
            return true
        }
    }


}
