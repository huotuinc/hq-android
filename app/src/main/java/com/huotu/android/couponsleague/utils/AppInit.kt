package com.huotu.android.couponsleague.utils

import android.app.Application
import android.support.text.emoji.EmojiCompat
import android.support.text.emoji.FontRequestEmojiCompatConfig
import android.support.text.emoji.bundled.BundledEmojiCompatConfig
import android.support.v4.provider.FontRequest
import android.util.Log
import com.facebook.drawee.backends.pipeline.Fresco
import com.liulishuo.filedownloader.FileDownloader
import com.squareup.leakcanary.LeakCanary


object AppInit {

    fun init(context :Application){

        CrashHandler.instance.init(context)

        Fresco.initialize( context)

        if(!LeakCanary.isInAnalyzerProcess(context)){
            LeakCanary.install(context)
        }

        //初始化下载组件库
        FileDownloader.setupOnApplicationOnCreate(context)

        //initEmoji(context)
    }

    private fun initEmoji(applicationContext:Application){


        val config: EmojiCompat.Config
        //if (USE_BUNDLED_EMOJI) {
            // Use the bundled font for EmojiCompat
            config = BundledEmojiCompatConfig(applicationContext)
        //} else {
            // Use a downloadable font for EmojiCompat
//            val fontRequest = FontRequest(
//                    "com.google.android.gms.fonts",
//                    "com.google.android.gms",
//                    "Noto Color Emoji Compat",
//                    R.array.com_google_android_gms_fonts_certs)
//
//            config = FontRequestEmojiCompatConfig(applicationContext, fontRequest)
//                    .setReplaceAll(true)
//                    .registerInitCallback(object : EmojiCompat.InitCallback() {
//                        override fun onInitialized() {
//                            Log.i("AppInit", "EmojiCompat initialized")
//                        }
//
//                        override fun onFailed(throwable: Throwable?) {
//                            Log.e("AppInit", "EmojiCompat initialization failed", throwable)
//                        }
//                    })
        //}
        EmojiCompat.init(config)
    }

}