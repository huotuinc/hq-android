package com.huotu.android.couponsleague.base

import android.app.Application
import com.huotu.android.couponsleague.bean.Variable
import com.huotu.android.couponsleague.utils.AppInit

class BaseApplication :Application() {
    //private var instance :BaseApplication?=null
    var variable : Variable = Variable()

    override fun onCreate() {
        super.onCreate()
        instance = this

        AppInit.init(this)
    }

    companion object {
        var instance :BaseApplication?=null
    }
}