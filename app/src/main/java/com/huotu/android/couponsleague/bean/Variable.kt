package com.huotu.android.couponsleague.bean

data class Variable (
    /**
     * 用户信息
     */
    var userBean: UserBean? = null,
    var initDataBean:InitDataBean?=null,
    var platType:Int = PlatTypeEnum.PINDUODUO.type
)