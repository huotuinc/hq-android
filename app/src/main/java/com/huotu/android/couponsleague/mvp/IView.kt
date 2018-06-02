package com.huotu.android.couponsleague.mvp

interface IView<T> {
    fun showProgress( msg:String)

    fun hideProgress()

    fun toast(msg : String)

    fun error(err:String)
}