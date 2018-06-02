package com.huotu.android.couponsleague.utils

import android.content.Context

object DensityUtils{

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    fun dip2px(context: Context?, dpValue: Float): Int {
        val scale = context!!.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    fun px2dip(context: Context, pxValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (pxValue / scale + 0.5f).toInt()
    }

    /**
     * 获得屏幕密度
     * @方法描述：
     * @方法名：getScreenDensity
     * @参数：@param aty
     * @参数：@return
     * @返回：float
     * @exception
     * @since
     */
    fun getScreenDensity(aty: Context): Float {
        var dm = aty.resources.displayMetrics
        return dm.density
    }

    /**
     * 获取屏幕宽度
     */
    fun getScreenWidth(aty: Context): Int {
        var dm = aty.resources.displayMetrics
        return dm.widthPixels
    }

    /**
     * 获取屏幕高度
     */
    fun getScreenHeight(aty: Context): Int {
        var dm = aty.resources.displayMetrics
        return dm.heightPixels
    }
}