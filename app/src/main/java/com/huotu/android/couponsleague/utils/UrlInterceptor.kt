package com.huotu.android.couponsleague.utils

import android.app.Activity
import android.content.Intent
import android.text.TextUtils
import android.webkit.WebView
import com.huotu.android.couponsleague.activity.LoginRegisterActivity
import com.huotu.android.couponsleague.bean.Constants

/**
 * Created by jinxiangdong on 2017/12/23.
 */

class UrlInterceptor(private val context: Activity) {
    //var isCarrierSuccessFlag:Boolean=false

    fun shouldOverrideUrlLoading(webView: WebView, url: String?): Boolean {

//        if(isCarrierSuccessFlag){
//            var intent = Intent(Constants.ACTION_AUTH_CHANGE)
//            context.sendBroadcast(intent)
//            context.finish()
//            return true
//        }

        if (!TextUtils.isEmpty(url) && url!!.contains("/authentication?t=zmxy")) {
            //拦截这个地址(芝麻认证回调地址)，然后关闭
            var intent = Intent(Constants.ACTION_AUTH_CHANGE)
            context.sendBroadcast(intent)
            context.finish()
            return true
        }
//        else if(!TextUtils.isEmpty(url) && url!!.contains("/result/") && url!!.contains("/suc")){
//            if(!isCarrierSuccessFlag){
//                isCarrierSuccessFlag=true
//                webView.loadUrl(url)
//            }
//            return true
//        }
        else if (!TextUtils.isEmpty(url) && url!!.contains("/login")) {
            val intent = Intent(context, LoginRegisterActivity::class.java)
            context.startActivity(intent)
            context.finish()
            return true
        } else if (!TextUtils.isEmpty(url) && url!!.contains("zhengxinapplogin")) {
            //拦截这个地址，然后进行原生的登录界面
            context.finish()
            val intent = Intent(context, LoginRegisterActivity::class.java)
            context.startActivity(intent)
            return true
       }
// else if (!TextUtils.isEmpty(url) && url!!.contains("zhengxinappauth")) {
//            //拦截这个地址，然后进行原生的认证界面
//            val intent = Intent(context, AuthItemActivity::class.java)
//            context.startActivity(intent)
//            return true
//        }
        else {
            webView.loadUrl(url)
            //            Intent intent = new Intent(context , WebActivity.class);
            //            intent.putExtra(Constants.INTENT_URL , url);
            //            context.startActivity(intent);

            return true
        }

        return false
    }
}
