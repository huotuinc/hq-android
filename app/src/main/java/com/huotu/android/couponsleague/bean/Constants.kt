package com.huotu.android.couponsleague.bean

object Constants {
    val OS_TYPE:String="android"
    val APP_SECRET:String="4165a8d240b29af3f41818d10599d0d1"
    val MerchantId:String="1"
    val ChannelId:String="recycleapp"

    val READ_TIMEOUT :Long= 15
    val CONNECT_TIMEOUT :Long= 15
    val WRITE_TIMEOUT :Long= 15
    //val BASE_URL:String ="http://192.168.1.210:8082/api/"
    val BASE_URL :String="http://youxin.51huotao.com/api/"
    //val YOUXIN_BASE_URL :String="http://192.168.1.210:8082/api/"
    val YOUXIN_BASE_URL:String="http://youxin.51huotao.com/api/"

    /**
     * app版本检测地址
     */
    var app_check_url = BASE_URL + "recycle/sys/checkUpdate"


    var MESSAGE_TOKEN_LOST = "你的账号在其他设备登录，请重新登录"
    var MESSAGE_ERROR = "发生错误,请重试"
    var TIP_LOADING = "加载中..."
    var TIP_PROCESSING = "处理中..."

    val INTENT_GOODSID="goodsid"
    val INTENT_GOODSNAME ="goodsname"
    val INTENT_MONEY = "money"
    val INTENT_SPEC="spec"
    val INTENT_URL="url"
    val INTENT_CANBACK = "canback"
    val INTENT_CARDNO = "cardno"
    val INTENT_PHONE="phone"
    val INTENT_AUTH_FLAG="auth_flag"
    val INTENT_AUTH_STATUS = "auth_status"
    val INTENT_AUTH_CLOSE = "auth_close"


    val PREF_FILENAME="pref_filename_phonerecycle"
    var PREF_USER = "pref_user"
    val PREF_SEARCH_FILENAME="pref_filename_search"
    var PREF_KEY="pref_key"
    //var PREF_YX_USER = "pref_yx_user"

    val ACTION_AUTH_FINISH="com.huotu.android.phonerecycle.action_auth_finish"
    val ACTION_AUTH_CHANGE="com.huotu.android.phonerecycle.action_auth_change"
    val ACTION_LOGOUT="com.huotu.android.phonerecycle.action_logout"
    val ACTION_ORDER_CHANGE="com.huotu.android.phonerecycle.action_order_change"

}