package com.huotu.android.phonerecycle.mvp.presenter


import com.huotu.android.couponsleague.bean.ApiResult
import com.huotu.android.couponsleague.bean.Constants
import com.huotu.android.couponsleague.bean.InitDataBean
import com.huotu.android.couponsleague.mvp.contract.SplashContract
import com.huotu.android.couponsleague.mvp.model.SplashModel
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class SplashPresenter(view: SplashContract.View) :SplashContract.Presenter {
    var mView:SplashContract.View?=null
    val mModel : SplashModel by lazy { SplashModel() }
    init {
        mView = view
    }


    override fun initData() {
        val observable : Observable<ApiResult<InitDataBean>>? = mModel.initData()
        observable?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe( object : Observer<ApiResult<InitDataBean>> {
                    override fun onComplete() {
                        mView!!.hideProgress()
                    }

                    override fun onSubscribe(d: Disposable) {
                        mView!!.showProgress(Constants.TIP_LOADING)
                    }

                    override fun onNext(t: ApiResult<InitDataBean>) {
                        mView!!.initDataCallback( t )
                    }

                    override fun onError(e: Throwable) {
                        mView!!.hideProgress()
                        mView!!.error(Constants.MESSAGE_ERROR)
                    }
                } )
    }

//    override fun readCityData() {
//        val observable : Observable<ArrayList<Province>>? = mModel.readCityData()
//        observable?.subscribeOn(Schedulers.io())
//                ?.observeOn(AndroidSchedulers.mainThread())
//                ?.subscribe( object : Observer<ArrayList<Province>> {
//                    override fun onComplete() {
//                        mView!!.hideProgress()
//                    }
//
//                    override fun onSubscribe(d: Disposable) {
//                        mView!!.showProgress(Constants.TIP_LOADING)
//                    }
//
//                    override fun onNext(t: ArrayList<Province>) {
//                        mView!!.readCityDataCallback( t )
//                    }
//
//                    override fun onError(e: Throwable) {
//                        mView!!.hideProgress()
//                        mView!!.error(Constants.MESSAGE_ERROR)
//                    }
//                } )
//    }

    override fun onDestory() {

    }
}