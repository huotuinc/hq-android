package com.huotu.android.couponsleague.bean

data class FavoriteBean(var pid:Int ,
                        var selected:Boolean=false ,
                       var goodsId :Long,
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