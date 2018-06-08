package com.huotu.android.couponsleague.adapter

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.huotu.android.couponsleague.R
import com.huotu.android.couponsleague.bean.Quan
import com.huotu.android.couponsleague.utils.DensityUtils
import com.huotu.android.couponsleague.widget.ExpandableTextView
import com.huotu.android.couponsleague.widget.video.SampleCoverVideo
import com.shuyu.gsyvideoplayer.GSYVideoManager
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder
import com.shuyu.gsyvideoplayer.listener.GSYSampleCallBack
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer

class QuanAdapter(data:ArrayList<Quan>) :BaseQuickAdapter< Quan ,BaseViewHolder>( R.layout.layout_quan_item_one , data) {

    val TAG = "QuanAdapter"

    var imageView :ImageView?=null

//    init {
//        imageView = ImageView(mContext)
//        imageView!!.scaleType = ImageView.ScaleType.CENTER_CROP
//    }



    private var gsyVideoOptionBuilder= GSYVideoOptionBuilder()

    override fun convert(helper: BaseViewHolder?, item: Quan?) {

        helper!!.addOnClickListener(R.id.quan_item_one_save_image)
        helper!!.addOnClickListener(R.id.quan_item_one_share)

        Glide.with(mContext.applicationContext)
                .setDefaultRequestOptions(
                        RequestOptions()
                                //.frame(1000000)
                                .centerCrop()).load("http://image.tkcm888.com/adSet_2018-05-31_a13475823f524d5f8b3b9480673e339915277602221601122.png")
                .into( helper!!.getView<ImageView>(R.id.quan_item_one_save_logo) )

        setNineImage( helper!!.getView(R.id.quan_item_one_images) , item!!.images )


        helper!!.getView<ExpandableTextView>(R.id.quan_item_one_content).text= item!!.content

        if(imageView==null) {
            imageView = ImageView(mContext)
        }else{
            if (imageView!!.parent != null) {
                val viewGroup = imageView!!.parent as ViewGroup
                viewGroup.removeView(imageView)
            }
        }


        imageView!!.scaleType = ImageView.ScaleType.CENTER_CROP
        imageView!!.setImageResource(R.mipmap.ic_launcher)
        if (imageView!!.parent != null) {
            val viewGroup = imageView!!.parent as ViewGroup
            viewGroup.removeView(imageView)
        }

        var url = "http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f20.mp4"
        var title ="测试视频"
        var position = helper!!.layoutPosition
        var gsyVideoPlayer = helper.getView<SampleCoverVideo>(R.id.quan_item_one_video)

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


        //gsyVideoPlayer.loadCoverImage( "http://image.tkcm888.com/adSet_2018-05-31_a13475823f524d5f8b3b9480673e339915277602221601122.png" , R.mipmap.ic_launcher)


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
        standardGSYVideoPlayer.startWindowFullscreen( mContext , false , true)
    }

    private fun setNineImage( recyclerView: RecyclerView , urls :ArrayList<String>?){
        if(urls==null||urls.size<1 )return

        var count = urls.size
        var width = 0
        if(count<3){
            recyclerView.layoutManager=StaggeredGridLayoutManager(count , StaggeredGridLayoutManager.VERTICAL)
            width = DensityUtils.getScreenWidth(mContext)/count
        }else{
            recyclerView.layoutManager = StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL)
            width = DensityUtils.getScreenWidth(mContext)/3
        }
        //recyclerView.addItemDecoration(Rec)

        var imageAdaper = ImageAdaper( width , urls)
        recyclerView.adapter=imageAdaper
    }
}