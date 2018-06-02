package com.huotu.android.couponsleague.bean

enum class MessageTypeEnum(var id:Int , var desc:String) {
    SystemMessage(1,"系统通知"),
    BusinessMessage(2,"业务通知"),
    ActivityMessage(3,"活动通知"),
}