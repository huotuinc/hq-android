package com.huotu.android.couponsleague.bean

data class ApiResult<T> (
    var resultCode:Int=0,
    var resultMsg:String="",
    var data:T?=null
    )