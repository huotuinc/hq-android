package com.huotu.android.couponsleague.utils

import android.content.Context
import android.view.Gravity
import android.view.View
import android.widget.Toast
import com.huotu.android.couponsleague.base.BaseApplication


class ToastUtils {
    private var toast: Toast? = null

    fun showShortToast(msg: String) {
        showToast(msg, Toast.LENGTH_SHORT)
    }

    private fun showToast(msg: String, duration: Int) {
        if (toast == null) {
            toast = Toast.makeText(BaseApplication.instance , msg, duration)
        }
        toast!!.duration = duration
        toast!!.setText(msg)
        toast!!.show()
    }

    fun showLongToast(msg: String) {
        showToast(msg, Toast.LENGTH_LONG)
    }

    fun showToast(context: Context, msg: String, resId: Int, duration: Int) {
        val t = Toast.makeText(BaseApplication.instance , msg, duration)
        t.setGravity(Gravity.CENTER, 0, 0)
        val v = View(context)
        v.setBackgroundResource(resId)
        t.view = v
        t.setText(msg)
        t.show()
    }


    companion object {
        var single = ToastUtils()
        fun getInstance():ToastUtils{
            return single
        }
    }

}
