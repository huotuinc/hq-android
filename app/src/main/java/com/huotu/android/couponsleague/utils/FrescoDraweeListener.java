package com.huotu.android.couponsleague.utils;

import android.graphics.drawable.Animatable;
import android.view.ViewGroup;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;
import java.lang.ref.WeakReference;

/**
 * 版权所有:杭州火图科技有限公司
 * 地址:浙江省杭州市滨江区西兴街道阡陌路智慧E谷B幢4楼在地图中查看
 * <p>
 * (c) Copyright Hangzhou Hot Technology Co., Ltd.
 * Floor 4,Block B,Wisdom E Valley,Qianmo Road,Binjiang District
 * 2013-2017.year. All rights reserved.
 * <p>
 * Created by jinxiangdong on 2017/11/17.
 */
public class FrescoDraweeListener extends BaseControllerListener<ImageInfo> {
    WeakReference<SimpleDraweeView> ref;
    int width;
    int height;
    ImageCallback imageCallback;
    public interface ImageCallback{
        void imageCallback(int  width, int height , SimpleDraweeView simpleDraweeView );
    }
    public void setImageCallback(ImageCallback imageCallback){
        this.imageCallback = imageCallback;
    }

    public FrescoDraweeListener(SimpleDraweeView iv, int width  , int height){
        this.ref= new WeakReference<>(iv);
        this.width=width;
        this.height=height;
    }
    @Override
    public void onFinalImageSet(String id, ImageInfo imageInfo, Animatable animatable) {
        super.onFinalImageSet(id, imageInfo, animatable);
        if( imageInfo==null) return;
        if( ref.get() ==null ) return;

        int h = imageInfo.getHeight();
        int w = imageInfo.getWidth();

        int ivw = width;
        int ivh = h* ivw / w;
        ViewGroup.LayoutParams layoutParams =  ref.get().getLayoutParams();
        layoutParams.width = ivw;
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;

        ref.get().setLayoutParams(layoutParams);

        float ratio = w * 1.0f/h;
        ref.get().setAspectRatio(ratio);

        if(imageCallback!=null){
            imageCallback.imageCallback( ivw,ivh , ref.get() );
        }

    }

    @Override
    public void onFailure(String id, Throwable throwable) {
        super.onFailure(id, throwable);
        if( ref.get() ==null ) return;

        ViewGroup.LayoutParams layoutParams = ref.get().getLayoutParams();
        layoutParams.width = width;
        layoutParams.height = height;
        ref.get().setLayoutParams(layoutParams);
        //float ratio = 1.0f;
        //ref.get().setAspectRatio(ratio);
    }

}
