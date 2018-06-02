package com.huotu.android.couponsleague.bean

data class GoodBean (var id :Int, var name :String , var price : String ,
                     var count:String , var logo:String , var images:ArrayList<String>?,
                     var price1 :String , var price2:String,
                     var price3:String ,var quan:String,var time:String , var desc:String)


data class DetailBean(var id :Int , var type:Int, var url:String)