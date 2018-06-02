package com.huotu.android.couponsleague.utils

import android.content.Context
import android.content.SharedPreferences
import java.util.*

class SPUtils private constructor(context: Context , spName : String){
    private val sp:SharedPreferences

    init {
        sp = context.getSharedPreferences(spName, Context.MODE_PRIVATE)
    }


    fun writeInt( key : String ,  value: Int){
        val editor = sp.edit()
        editor.putInt(key, value)
        editor.apply()
    }

    fun writeLong(key:String , value:Long){
        sp.edit().putLong(key , value ).apply()
    }

    fun writeBoolean(key :String , value:Boolean){
        sp.edit().putBoolean(key , value).apply()
    }

    fun writeString(key:String , value:String){
        sp.edit().putString(key , value).apply()
    }

    fun writeStringSet(key:String , value :Set<String>){
        sp.edit().putStringSet(key , value).apply()
    }

    fun readInt(key:String , defaultValue: Int=0):Int{
        return sp.getInt(key, defaultValue)
    }


    fun readLong(key:String , defaultValue: Long=0):Long{
        return sp.getLong(key, defaultValue)
    }


    fun readBoolean(key:String , defaultValue: Boolean=false):Boolean{
        return sp.getBoolean(key, defaultValue)
    }


    fun readString(key:String):String{
        return sp.getString(key,null)
    }

    fun readString(key:String,defaultValue:String=""):String{
        return sp.getString(key,defaultValue)
    }

    fun readStringSet(key:String , defaultValue: Set<String> = Collections.emptySet() ):Set<String>{
        return sp.getStringSet(key , defaultValue )
    }

    fun remove(key:String){
        sp.edit().remove(key).apply()
    }

    fun clear(){
        sp.edit().clear().apply()
    }

    companion object {
        private val spMap = HashMap<String , SPUtils>()

        fun getInstance(context: Context , spName:String):SPUtils{
            var sp :SPUtils? = spMap[spName]
            if(sp ==null){
                sp = SPUtils(context , spName)
                spMap.put(spName, sp)
            }
            return sp
        }
    }
}