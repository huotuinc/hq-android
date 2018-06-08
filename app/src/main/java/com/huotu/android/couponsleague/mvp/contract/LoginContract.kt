package com.huotu.android.couponsleague.mvp.contract

import com.huotu.android.couponsleague.bean.ApiResult
import com.huotu.android.couponsleague.bean.UserBean
import com.huotu.android.couponsleague.mvp.IPresenter
import com.huotu.android.couponsleague.mvp.IView


interface LoginContract {
    interface Presenter: IPresenter {
        fun register(mobile:String, inviteCode:String , smsCode : String)

        fun login(phone :String , code :String)

        fun sendVerifyCode(phone:String)
    }

    interface View: IView<Presenter> {
        fun registerCallback(apiResult: ApiResult<UserBean>)

        fun loginCallback(apiResult: ApiResult<UserBean>)

        fun sendVerifyCodeCallback(apiResult: ApiResult<Any>)
    }
}