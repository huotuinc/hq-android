package com.huotu.android.couponsleague.mvp.contract

import com.huotu.android.couponsleague.bean.ApiResult
import com.huotu.android.couponsleague.bean.InitDataBean
import com.huotu.android.couponsleague.mvp.IPresenter
import com.huotu.android.couponsleague.mvp.IView


interface SplashContract {
    interface Presenter: IPresenter {

        fun initData()

        /**
         * 读取省市区数据
         */
        //fun readCityData()
    }
    interface View: IView<Presenter> {

         fun initDataCallback(result: ApiResult<InitDataBean>)

         //fun readCityDataCallback(list: ArrayList<Province>)

    }
}