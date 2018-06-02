package com.huotu.android.couponsleague.mvp.model

import com.huotu.android.couponsleague.bean.ApiResult
import com.huotu.android.couponsleague.bean.UserBean
import com.huotu.android.couponsleague.http.RetrofitManager
import io.reactivex.Observable

class LoginModel{

    fun loginByVerifyCode(phone:String ,code :String ) : Observable<ApiResult<UserBean>>{
        val apiService = RetrofitManager.getApiService()
        return apiService!!.loginByVerifyCode(phone, code )
    }

    fun sendVerifyCode(phone:String):Observable<ApiResult<Any>>{
        val apiService = RetrofitManager.getApiService()
        return apiService!!.sendVerifyCode(phone )
    }
}
