package com.huotu.android.couponsleague.bean.recommand

enum class ItemTypeEnum(var type :Int,var desc:String){
    BANNER(1 , "Banner样式"),
    ONE_COLLOMN_SIMPLE(2,"一列一张图片样式"),
    ONE_ROW_COUNTDOWN(3,"一行倒计时"),
    ONE_ROW_GOODS(4,"一行显示商品信息样式"),
    ONE_ROW_TITLE(5,"一行标题样式"),
    ONE_ROW_CAN_SCROLL_BANNER(6,"一行显示多张图片可滑动行样式"),
    ONE_COLLOMN_GOODS(7,"一列显示商品信息样式"),

}