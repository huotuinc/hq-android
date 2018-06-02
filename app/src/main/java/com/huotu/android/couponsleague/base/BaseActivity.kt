package com.huotu.android.couponsleague.base

import android.content.pm.ActivityInfo
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.KeyEvent
import android.view.View
import com.huotu.android.couponsleague.R
import com.huotu.android.couponsleague.activity.LoginRegisterActivity
import com.huotu.android.couponsleague.bean.ApiResultCodeEnum
import com.huotu.android.couponsleague.bean.Constants
import com.huotu.android.couponsleague.mvp.IView
import com.huotu.android.couponsleague.utils.StatusBarUtils
import com.huotu.android.couponsleague.utils.newIntent
import com.huotu.android.couponsleague.utils.showToast


open class BaseActivity<T> : AppCompatActivity() , IView<T> {

    var isWhite:Boolean=true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setStatusBar()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            this.finish()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    open fun setStatusBar(){
        //禁止横屏
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        if(isWhite){
            setStatusBarTextBlackColor()
        }else {
            StatusBarUtils.setColor(this, resources.getColor( R.color.my_statusbar_color ) )
        }
    }


    private fun setStatusBarTextBlackColor():Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            var newUiVisibility = this.window.decorView.systemUiVisibility
            newUiVisibility = newUiVisibility or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            newUiVisibility = newUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            this.window.decorView.systemUiVisibility = newUiVisibility
            window.statusBarColor = Color.WHITE
            return true
        }
        return false
    }


    override fun showProgress(msg: String) {

    }

    override fun hideProgress() {

    }

    override fun toast(msg: String) {
        showToast(msg)
    }

    override fun error(err: String) {
        toast(err)
    }


    /**
     * 处理服务端返回的公共错误代码
     * @param apiResult
     * @return
     */
    protected fun processCommonErrorCode(code :Int ,  msg:String? ): Boolean {
        if (//apiResult.getResultCode() == ApiResultCodeEnum.PARAMETER_ERROR.getCode() ||
        //apiResult.getResultCode() == ApiResultCodeEnum.SIGN_NOT_PASS.getCode() ||
        //apiResult.getResultCode() == ApiResultCodeEnum.SIGN_ERROR .getCode()  ||
                code == ApiResultCodeEnum.TOKEN_ERROR.code ) {
            //toast( apiResult.getResultMsg());
            toast(Constants.MESSAGE_TOKEN_LOST)

            //EventBus.getDefault().post(LogoutEvent())

            newIntent<LoginRegisterActivity>()
            return true
        }
        return false
    }

    protected fun isLogin(): Boolean {
        return if ( BaseApplication.instance!!.variable.userBean == null
                || BaseApplication.instance!!.variable.userBean!!.userId == 0L) false
                else true
    }

}
