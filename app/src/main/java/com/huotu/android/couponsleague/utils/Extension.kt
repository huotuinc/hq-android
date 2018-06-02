package com.huotu.android.couponsleague.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.Gravity
import android.widget.Toast
import com.huotu.android.couponsleague.activity.LoginRegisterActivity
import com.huotu.android.couponsleague.base.BaseApplication


fun Context.showToast(message:String): Toast {
        var toast : Toast = Toast.makeText(this, message, Toast.LENGTH_LONG)
        toast.setGravity(Gravity.CENTER,0,0)
        toast.show()
        return toast
    }

    inline fun <reified T: Activity> Activity.newIntent() {
        val intent = Intent(this, T::class.java)
        startActivity(intent)
    }

    inline fun<reified T: Activity> Activity.newIntent(key:String, value:String?){
        val intent = Intent(this, T::class.java)
        intent.putExtra(key,value)
        startActivity(intent)
    }

    inline fun<reified T: Activity> Activity.newIntent(key:String, value:Long=0){
        val intent = Intent(this, T::class.java)
        intent.putExtra(key,value)
        startActivity(intent)
    }

    inline fun<reified T: Activity> Activity.newIntent(key:String, value:Boolean=false){
        val intent = Intent(this, T::class.java)
        intent.putExtra(key,value)
        startActivity(intent)
    }

    inline fun<reified T: Activity> Activity.newIntent(bundle: Bundle){
        val intent = Intent(this, T::class.java)
        intent.putExtras(bundle)

        startActivity(intent)
    }

    inline fun<reified T: Activity> Activity.skipIntent(){
        val intent = Intent(this, T::class.java)
        startActivity(intent)
        finish()
    }

    inline fun<reified T: Activity> Activity.skipIntent(key:String, value:Long=0){
        val intent = Intent(this, T::class.java)
        intent.putExtra(key,value)
        startActivity(intent)
        finish()
    }

    inline fun<reified T: Activity> Activity.skipIntent(key:String, value:String){
        val intent = Intent(this, T::class.java)
        intent.putExtra(key,value)
        startActivity(intent)
        finish()
    }

    inline fun<reified T: Activity> Activity.skipIntent(bundle: Bundle){
        val intent = Intent(this, T::class.java)
        intent.putExtras(bundle)
        startActivity(intent)
        finish()
    }

    inline fun<reified T: Activity> Activity.skipIntentForLogin(bundle: Bundle){
        if ( BaseApplication.instance!!.variable.userBean == null
                || BaseApplication.instance!!.variable.userBean?.userId == 0L) {
            skipIntent<LoginRegisterActivity>()
            return
        }

        skipIntent<T>(bundle)
    }


    inline fun<reified T: Activity> Activity.newIntentForResult(requestCode:Int ){
        val intent = Intent(this, T::class.java)
        startActivityForResult(intent , requestCode)
    }

    inline fun<reified T: Activity> Activity.newIntentForResult(requestCode:Int, key :String, value:String?){
        val intent = Intent(this, T::class.java)
        intent.putExtra(key , value )
        startActivityForResult(intent , requestCode)
    }

    inline fun<reified T: Activity> Activity.newIntentForResult(requestCode : Int, bundle: Bundle){
        val intent = Intent(this, T::class.java)
        intent.putExtras(bundle)
        startActivityForResult(intent , requestCode)
    }

    inline fun<reified T: Activity> Activity.newIntentForLogin(){

        if ( BaseApplication.instance!!.variable.userBean == null
                || BaseApplication.instance!!.variable.userBean?.userId == 0L) {
            newIntent<LoginRegisterActivity>()
        }
        else {
            newIntent<T>()
        }
    }

    inline fun<reified T: Activity> Activity.newIntentForLogin(key : String, value:Long ){

        if ( BaseApplication.instance!!.variable.userBean == null
                || BaseApplication.instance!!.variable.userBean?.userId == 0L) {
            newIntent<LoginRegisterActivity>()
        }
        else {
            newIntent<T>(key , value)
        }
    }

    inline fun<reified T: Activity> Activity.newIntentForLogin(key : String, value:String ){

        if ( BaseApplication.instance!!.variable.userBean == null
                || BaseApplication.instance!!.variable.userBean?.userId == 0L) {
            newIntent<LoginRegisterActivity>()
        }
        else {
            newIntent<T>(key , value)
        }
    }

    inline fun<reified T: Activity> Activity.newIntentForLogin(bundle: Bundle){

        if ( BaseApplication.instance!!.variable.userBean == null
                || BaseApplication.instance!!.variable.userBean?.userId == 0L) {
            newIntent<LoginRegisterActivity>()
        }
        else {
            newIntent<T>(bundle)
        }
    }


    inline fun<reified T: Activity> Fragment.newIntent() {
        val intent = Intent(this.context, T::class.java)
        startActivity(intent)
    }