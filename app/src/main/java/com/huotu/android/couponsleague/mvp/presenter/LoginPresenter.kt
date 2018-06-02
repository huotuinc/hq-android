package com.huotu.android.couponsleague.mvp.presenter

import com.huotu.android.couponsleague.bean.ApiResult
import com.huotu.android.couponsleague.bean.Constants
import com.huotu.android.couponsleague.bean.UserBean
import com.huotu.android.couponsleague.mvp.contract.LoginContract
import com.huotu.android.couponsleague.mvp.model.LoginModel
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class LoginPresenter(view:LoginContract.View): LoginContract.Presenter{
    var mView:LoginContract.View?=null
    val mModel: LoginModel by lazy { LoginModel() }

    init {
        mView=view
    }


    override fun sendVerifyCode(phone: String) {
        val observable : Observable<ApiResult<Any>>? = mModel.sendVerifyCode(phone )
        observable?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe( object : Observer<ApiResult<Any>>{
                    override fun onComplete() {
                        mView!!.hideProgress()
                    }

                    override fun onSubscribe(d: Disposable) {
                        mView!!.showProgress(Constants.TIP_LOADING)
                    }

                    override fun onNext(t: ApiResult<Any>) {
                        mView!!.sendVerifyCodeCallback( t )
                    }

                    override fun onError(e: Throwable) {
                        mView!!.hideProgress()
                        mView!!.error(Constants.MESSAGE_ERROR)
                    }
                } )
    }

    override fun loginByVerifyCode(phone:String, code :String ) {
        val observable : Observable<ApiResult<UserBean>>? = mModel.loginByVerifyCode(phone,code )
        observable?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe( object : Observer<ApiResult<UserBean>>{
                    override fun onComplete() {
                        mView!!.hideProgress()
                    }

                    override fun onSubscribe(d: Disposable) {
                        mView!!.showProgress(Constants.TIP_LOADING)
                    }

                    override fun onNext(t: ApiResult<UserBean>) {
                        mView!!.loginByVerifyCodeCallback( t )
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
