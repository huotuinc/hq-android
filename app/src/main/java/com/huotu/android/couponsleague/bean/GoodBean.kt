package com.huotu.android.couponsleague.bean

data class GoodBean (var goodsId :Long,
                     var title :String ,
                     var platform : String ,
                     var goodsPrice : String ,
                     var salesVolume :String ,
                     var imgSrc :String ,
                     var images:ArrayList<String>?,
                     var couponPrice :String ,
                     var finalPrice :String,
                     var isFav :String,
                     var earnMoney :String ,
                     var time:String ,
                     var goodsIntro:String)

data class DetailBean(var id :Int , var type:Int, var url:String)