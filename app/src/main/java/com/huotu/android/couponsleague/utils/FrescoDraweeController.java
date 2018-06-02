package com.huotu.android.couponsleague.utils;

import android.graphics.drawable.DrawableWrapper;
import android.net.Uri;
import android.text.TextUtils;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

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
public class FrescoDraweeController {

    public static void loadImage(SimpleDraweeView simpleDraweeView , int width , int height , String url){
        if(TextUtils.isEmpty(url))return;
        ResizeOptions resizeOptions = new ResizeOptions(width , height);

        ImageRequest imageRequest = ImageRequestBuilder
                .newBuilderWithSource(Uri.parse(url))
                .setResizeOptions(resizeOptions)
                .build();

        DraweeController draweeController= Fresco.newDraweeControllerBuilder()
                .setOldController(simpleDraweeView.getController())
                .setImageRequest(imageRequest)
                .setTapToRetryEnabled(true)
                .build();

        simpleDraweeView.setController(draweeController);
    }

    public static void loadImage(SimpleDraweeView simpleDraweeView , int width , int height , String url , FrescoDraweeListener.ImageCallback imageCallback){
        FrescoDraweeListener listener = new FrescoDraweeListener(simpleDraweeView , width  , height );
        listener.setImageCallback(imageCallback);
        DraweeController draweeController = Fresco
                .newDraweeControllerBuilder()
                .setAutoPlayAnimations(true)
                .setTapToRetryEnabled(false)
                .setUri( url )
                .setOldController(simpleDraweeView.getController())
                .setControllerListener( listener )
                .build();
        simpleDraweeView.setController( draweeController);
    }

}
