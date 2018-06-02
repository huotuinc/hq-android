package com.huotu.android.couponsleague.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.huotu.android.couponsleague.R
import com.huotu.android.couponsleague.bean.Quan
import com.shuyu.gsyvideoplayer.GSYVideoManager
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder
import com.shuyu.gsyvideoplayer.listener.GSYSampleCallBack
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer

class QuanAdapter(data:ArrayList<Quan>) :BaseQuickAdapter< Quan ,BaseViewHolder>( R.layout.layout_quan_item_one , data) {

    val TAG = "QuanAdapter"

//    var imageView :ImageView?=null

//    init {
//        imageView = ImageView(mContext)
//        imageView!!.scaleType = ImageView.ScaleType.CENTER_CROP
//    }



    var gsyVideoOptionBuilder= GSYVideoOptionBuilder()

    override fun convert(helper: BaseViewHolder?, item: Quan?) {

        helper!!.addOnClickListener(R.id.quan_item_one_save_image)
        helper!!.addOnClickListener(R.id.quan_item_one_share)


        var imageView = ImageView( mContext)
        imageView!!.scaleType = ImageView.ScaleType.CENTER_CROP
        imageView.setImageResource(R.mipmap.splash)
        if (imageView!!.parent != null) {
            val viewGroup = imageView!!.parent as ViewGroup
            viewGroup.removeView(imageView)
        }

        var url = "http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f20.mp4"
        var title ="测试视频"
        var position = helper!!.layoutPosition
        var gsyVideoPlayer = helper.getView<StandardGSYVideoPlayer>(R.id.quan_item_one_video)

        gsyVideoOptionBuilder
                .setIsTouchWiget(false)
                .setThumbImageView(imageView)
                .setUrl(url)
                .setSetUpLazy(true)//lazy可以防止滑动卡顿
                .setVideoTitle(title)
                .setCacheWithPlay(true)
                .setRotateViewAuto(true)
                .setLockLand(true)
                .setPlayTag(TAG)
                .setShowFullAnimation(true)
                .setNeedLockFull(true)
                .setPlayPosition(position)
                .setVideoAllCallBack(object : GSYSampleCallBack() {
                    override fun onPrepared(url: String?, vararg objects: Any) {
                        super.onPrepared(url, *objects)
                        if (!gsyVideoPlayer.isIfCurrentIsFullscreen()) {
                            //静音
                            GSYVideoManager.instance().isNeedMute = false
                        }

                    }

                    override fun onQuitFullscreen(url: String?, vararg objects: Any) {
                        super.onQuitFullscreen(url, *objects)
                        //全屏不静音
                        GSYVideoManager.instance().isNeedMute = false
                    }

                    override fun onEnterFullscreen(url: String?, vararg objects: Any) {
                        super.onEnterFullscreen(url, *objects)
                        GSYVideoManager.instance().isNeedMute = false
                        gsyVideoPlayer.getCurrentPlayer().getTitleTextView().setText(objects[0] as String)
                    }
                }).build(gsyVideoPlayer)


        //增加title
        gsyVideoPlayer.getTitleTextView().setVisibility(View.GONE)

        //设置返回键
        gsyVideoPlayer.getBackButton().setVisibility(View.GONE)

        //设置全屏按键功能
        gsyVideoPlayer.getFullscreenButton().setOnClickListener(View.OnClickListener { resolveFullBtn(gsyVideoPlayer) })


    }

    /**
     * 全屏幕按键处理
     */
    private fun resolveFullBtn(standardGSYVideoPlayer: StandardGSYVideoPlayer) {
        standardGSYVideoPlayer.startWindowFullscreen( mContext , true, true)
    }
}