package com.huotu.android.couponsleague.http

import com.huotu.android.couponsleague.bean.Constants

data class HeaderParameter( var appVersion: String? = null,
            var hwid: String? = null,
            var mobileType: String? = null,
            var osType :String = Constants.OS_TYPE,
            var osVersion: String? = null,
            var userId: Long = 0,
            var userToken :String= "",
            var merchantId :String= "1",
            var cityCode: String? = null,
            var cityName: String? = null,
            var channelId: String? = "default")