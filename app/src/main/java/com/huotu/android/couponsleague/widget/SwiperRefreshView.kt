package com.huotu.android.couponleague.widget

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.util.AttributeSet
import com.huotu.android.couponsleague.R

/**
 * 版权所有:杭州火图科技有限公司
 * 地址:浙江省杭州市滨江区西兴街道阡陌路智慧E谷B幢4楼在地图中查看
 *
 *
 * (c) Copyright Hangzhou Hot Technology Co., Ltd.
 * Floor 4,Block B,Wisdom E Valley,Qianmo Road,Binjiang District
 * 2013-2017.year. All rights reserved.
 *
 *
 * Created by jinxiangdong on 2017/11/16.
 */
class SwiperRefreshView : SwipeRefreshLayout {
    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {

        this.setColorSchemeColors(
                ContextCompat.getColor(getContext(), R.color.colorPrimary)
        )

    }
}
