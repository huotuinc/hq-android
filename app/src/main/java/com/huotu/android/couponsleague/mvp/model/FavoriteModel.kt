package com.huotu.android.couponsleague.mvp.model

import com.huotu.android.couponsleague.bean.ApiResult
import com.huotu.android.couponsleague.bean.FavoriteBean
import com.huotu.android.couponsleague.bean.UserBean
import com.huotu.android.couponsleague.http.RetrofitManager
import io.reactivex.Observable

class FavoriteModel{

    fun favorite( goodsId : Long ): Observable<ApiResult<Any>>{
        val apiService = RetrofitManager.getApiService()
        return apiService!!.favorite( goodsId )
    }


    fun favoriteList( pageIndex : Long ): Observable<ApiResult<FavoriteBean>>{
        val apiService = RetrofitManager.getApiService()
        return apiService!!.favoriteList( pageIndex )
    }

}
