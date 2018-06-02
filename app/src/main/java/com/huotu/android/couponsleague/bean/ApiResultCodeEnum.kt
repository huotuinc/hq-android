package com.huotu.android.couponsleague.bean

enum class ApiResultCodeEnum (var code :Int,var desc :String ){

    SUCCESS(2000, "SUCCESS"),
    TOKEN_ERROR(4003, "token失效"),
    AUTH_ERROR(4123 ,"未完成认证,前往认证"),
    EXIST_RECYCLE_ERROR(4502,"存在回收中的记录，请完成后再次申请!")
}