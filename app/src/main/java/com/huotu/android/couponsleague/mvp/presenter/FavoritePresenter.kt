package com.huotu.android.couponsleague.mvp.presenter

import com.huotu.android.couponsleague.bean.ApiResult
import com.huotu.android.couponsleague.bean.Constants
import com.huotu.android.couponsleague.bean.FavoriteBean
import com.huotu.android.couponsleague.bean.UserBean
import com.huotu.android.couponsleague.mvp.contract.FavoriteContract
import com.huotu.android.couponsleague.mvp.contract.LoginContract
import com.huotu.android.couponsleague.mvp.model.FavoriteModel
import com.huotu.android.couponsleague.mvp.model.LoginModel
import com.trello.rxlifecycle2.LifecycleProvider
import com.trello.rxlifecycle2.kotlin.bindToLifecycle
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class FavoritePresenter(view: FavoriteContract.View): FavoriteContract.Presenter{
    var mView:FavoriteContract.View?=null
    private val mModel: FavoriteModel by lazy { FavoriteModel() }

    init {
        mView=view
    }

    override fun favorite(goodsId: Long) {
        val observable : Observable<ApiResult<Any>>? = mModel.favorite( goodsId )
        observable?.subscribeOn(Schedulers.io())
                ?.bindToLifecycle(mView as LifecycleProvider<*>)
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe( object : Observer<ApiResult<Any>>{
                    override fun onComplete() {
                        mView!!.hideProgress()
                    }

                    override fun onSubscribe(d: Disposable) {
                        mView!!.showProgress(Constants.TIP_LOADING)
                    }

                    override fun onNext(t: ApiResult<Any>) {
                        mView!!.favoriteCallback( t )
                    }

                    override fun onError(e: Throwable) {
                        mView!!.hideProgress()
                        mView!!.error(Constants.MESSAGE_ERROR)
                    }
                } )
    }

    override fun favoriteList(pageIndex: Long) {
        val observable : Observable<ApiResult<FavoriteBean>>? = mModel.favoriteList( pageIndex )
        observable?.subscribeOn(Schedulers.io())
                ?.bindToLifecycle(mView as LifecycleProvider<*>)
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe( object : Observer<ApiResult<FavoriteBean>>{
                    override fun onComplete() {
                        mView!!.hideProgress()
                    }

                    override fun onSubscribe(d: Disposable) {
                        mView!!.showProgress(Constants.TIP_LOADING)
                    }

                    override fun onNext(t: ApiResult<FavoriteBean>) {
                        mView!!.favoriteListCallback( t )
                    }

                    override fun onError(e: Throwable) {
                        mView!!.hideProgress()
                        mView!!.error(Constants.MESSAGE_ERROR)
                    }
                } )
    }

    override fun onDestory() {

    }
}
