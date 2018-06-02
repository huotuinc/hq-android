package com.huotu.android.couponsleague.mvp.presenter

import com.huotu.android.couponsleague.mvp.contract.MainContract
import com.huotu.android.couponsleague.mvp.model.MainModel

class MainPresenter(view: MainContract.View) :MainContract.Presenter{
        var mView: MainContract.View?=null
        val mModel: MainModel by lazy { MainModel() }

        init {
            mView=view
        }

    override fun onDestory() {

    }
}