package com.huotu.android.couponsleague.http

import com.huotu.android.couponsleague.bean.Constants
import com.huotu.android.couponsleague.bean.PlatTypeEnum

data class HeaderParameter( var appVersion: String? = null,
            var hwid: String? = null,
            var mobileType: String? = null,
            var osType :Int = Constants.OS_TYPE,
            var osVersion: String? = "",
            var userId: Long = 0,
            var userToken :String= "",
            var platType: Int = PlatTypeEnum.PINDUODUO.type )