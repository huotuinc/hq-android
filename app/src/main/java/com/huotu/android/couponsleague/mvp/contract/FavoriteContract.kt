package com.huotu.android.couponsleague.mvp.contract

import com.huotu.android.couponsleague.bean.ApiResult
import com.huotu.android.couponsleague.bean.FavoriteBean
import com.huotu.android.couponsleague.bean.UserBean
import com.huotu.android.couponsleague.mvp.IPresenter
import com.huotu.android.couponsleague.mvp.IView


interface FavoriteContract {
    interface Presenter: IPresenter {
        fun favorite(goodsId:Long)
        fun favoriteList(pageIndex:Long)
    }

    interface View: IView<Presenter> {
        fun favoriteCallback(apiResult: ApiResult<Any>)
        fun favoriteListCallback( apiResult: ApiResult<FavoriteBean>)
    }
}