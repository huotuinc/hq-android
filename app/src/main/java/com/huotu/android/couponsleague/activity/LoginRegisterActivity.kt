package com.huotu.android.couponsleague.activity

import android.Manifest
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.view.View
import cn.iwgang.countdownview.CountdownView
import com.huotu.android.couponsleague.R
import com.huotu.android.couponsleague.base.BaseActivity
import com.huotu.android.couponsleague.base.BaseApplication
import com.huotu.android.couponsleague.bean.ApiResult
import com.huotu.android.couponsleague.bean.ApiResultCodeEnum
import com.huotu.android.couponsleague.bean.Constants
import com.huotu.android.couponsleague.bean.UserBean
import com.huotu.android.couponsleague.mvp.contract.LoginContract
import com.huotu.android.couponsleague.mvp.presenter.LoginPresenter
import com.huotu.android.couponsleague.receiver.SmsReceiver
import com.huotu.android.couponsleague.utils.*
import kotlinx.android.synthetic.main.activity_loginregister.*
import java.util.regex.Pattern
import permissions.dispatcher.NeedsPermission
import permissions.dispatcher.RuntimePermissions


@RuntimePermissions
class LoginRegisterActivity : BaseActivity<LoginContract.Presenter>(),
        LoginContract.View, CountdownView.OnCountdownEndListener,
        View.OnFocusChangeListener,
        View.OnClickListener,
        SmsReceiver.SmsListener {

    internal var isAgress = true
    internal var countdown :Long = 60 * 1000
    internal var isRegister = false
    internal var smsReceiver: SmsReceiver? = null
    internal var presenter: LoginPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_loginregister)

        presenter = LoginPresenter(this)


        KeybordUtils.openKeybord(this , login_phone )
        login_code_hint.visibility = View.VISIBLE
        login_countdown.setOnCountdownEndListener(this)
        login_getcode.setOnClickListener(this)
        login_go.setOnClickListener(this)
        login_register.setOnClickListener(this)
        ll_text.setOnClickListener(this)
        login_protocal.setOnClickListener(this)

        clearLocalData()
        clearCookie()

        registerSmsReceiver()

        login_code.onFocusChangeListener=this
        login_code.addTextChangedListener(object:TextWatcher{
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val txt = login_code.getText().toString()
                if (TextUtils.isEmpty(txt)) {
                    login_code_hint.visibility = View.VISIBLE
                } else {
                    login_code_hint.visibility =View.GONE
                }

                checkEnableLogin()
            }
        })

        login_phone.addTextChangedListener(object:TextWatcher{
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                checkEnableLogin()
            }
            override fun afterTextChanged(s: Editable?) {
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        KeybordUtils.closeKeyboard(this)

        if (login_countdown != null) {
            login_countdown.setOnCountdownEndListener(null)
            login_countdown.stop()
        }

        if (smsReceiver != null) {
            smsReceiver!!.setSmsListener(null)
            this.unregisterReceiver(smsReceiver)
            smsReceiver = null
        }

    }

    fun setImmerseLayout() {
        setStatusBarTextBlackColor()
    }

    /**
     * 此功能在 android 6.0以上系统适用
     * 设置白底黑字的状态栏
     */
    private fun setStatusBarTextBlackColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            var newUiVisibility = this.window.decorView.systemUiVisibility
            newUiVisibility = newUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            this.window.decorView.systemUiVisibility = newUiVisibility
            StatusBarUtils.setColorNoTranslucent(this, ContextCompat.getColor(this, R.color.white))
        }
    }


    //@OnFocusChange(R.id.login_code)
    override fun onFocusChange(v: View, hasFocus: Boolean) {
        if (hasFocus) {
            login_code_hint.setVisibility(View.GONE)
        } else {
            val code = login_code.getText().toString()
            if (TextUtils.isEmpty(code)) {
                login_code_hint.setVisibility(View.VISIBLE)
            } else {
                login_code_hint.setVisibility(View.GONE)
            }
        }
    }


//    @OnTextChanged(R.id.login_phone)
//    fun textChangedPhone(s: CharSequence, start: Int, before: Int, count: Int) {
//        checkEnableLogin()
//    }



    //@OnClick(R.id.login_getcode, R.id.login_go, R.id.ll_text, R.id.login_protocal, R.id.login_zhima_lay)
    override fun onClick(v: View) {
        if (v.id == R.id.login_getcode) {
            //getCode();
            //LoginRegisterActivityPermissionsDispatcher.checkReceiveSmsWithPermissionCheck(this)

            checkReceiveSmsWithPermissionCheck()

        } else if (v.id == R.id.login_go) {
            go()
        } else if (v.id == R.id.ll_text) {
            isAgress = !isAgress
            login_agress.setImageResource(if (isAgress) R.mipmap.protocal else R.mipmap.protocal_unselected)
        } else if (v.id == R.id.login_protocal) {
            KeybordUtils.closeKeyboard(this)
            var url = if(BaseApplication.instance!!.variable.initDataBean==null) "" else BaseApplication.instance!!.variable.initDataBean!!.recycle_list_url
            newIntent<WebActivity>(Constants.INTENT_URL, url )
        }else if(v.id==R.id.login_register){
            newIntent<RegisterActivity>()
        }
    }

    protected fun go() {
        val phone = login_phone.textWithoutSpace.trim()
        val code = login_code.text.toString().trim()
        if (phone.isEmpty()) {
            login_phone.error ="请输入手机号码"
            login_phone.requestFocus()
            KeybordUtils.openKeybord(this , login_phone )
            return
        }

        if (TextUtils.isEmpty(code)) {
            login_code.error = "请输入短信验证码"
            login_code.requestFocus()
            KeybordUtils.openKeybord(this ,login_code)
            return
        }

        KeybordUtils.closeKeyboard(this)

        if (!isAgress) {
            toast("请阅读并同意《用户注册服务协议》")
            return
        }

//        if (!isRegister && login_zhima.getTag() == null) {
//            toast("请选择芝麻信用分")
//            selectZhima()
//            return
//        }

//        var zhimaCode = 0
//        if (!isRegister && login_zhima.getTag() != null) {
//            val kv = login_zhima.getTag() as KeyValue
//            zhimaCode = kv.getCode()
//        }

        presenter!!.loginByVerifyCode(phone, code )
    }

    protected fun getCode() {
        val phone = login_phone.textWithoutSpace.trim()
        if (phone.isEmpty()) {
            login_phone.setError("请输入手机号码")
            login_phone.requestFocus()
            return
        }
        if (!MobileUtils.isPhone(phone)) {
            login_phone.setError("请输入正确的手机号码")
            login_phone.requestFocus()
            return
        }

        presenter!!.sendVerifyCode(phone)
    }

    /**
     * 检测登录注册按钮是否可用
     */
    internal fun checkEnableLogin() {
        val phone = login_phone.textWithoutSpace.trim()
        val code = login_code.getText().toString().trim()
        //boolean isPhone = loginPhone.isContentCheck();
        if (phone.length >= 11 && !TextUtils.isEmpty(code)) {
            login_go.setBackgroundResource(R.drawable.style_login_button)
            login_go.setEnabled(true)
        } else {
            login_go.setEnabled(false)
            login_go.setBackgroundResource(R.drawable.style_login)
        }

        if (phone.length >= 11) {
            //login_zhima_lay.setEnabled(true);
            //iPresenter.checkReg(phone)
        } else {
            login_zhima.setText("")
            login_zhima.setTag(null)
            //login_zhima_lay.setEnabled(false);
        }
    }


    override fun onEnd(cv: CountdownView) {
        login_countdown.stop()
        login_countdown.setVisibility(View.GONE)
        login_getcode.setVisibility(View.VISIBLE)
    }

    fun loginCallback(userBean: UserBean) {

    }

    override fun sendVerifyCodeCallback( apiResult: ApiResult<Any>) {

        hideProgress()
        login_getcode.setVisibility(View.GONE)
        login_countdown.setVisibility(View.VISIBLE)
        login_countdown.start(countdown)

        login_code.requestFocus()

        login_code.setText("")

        KeybordUtils.openKeybord(this , login_code)
    }

//    fun RegisterCallback(userBean: UserBean) {
//
//    }

//    fun updatePasswordCallback(apiResult: ApiResult<Any>) {
//
//    }

    override fun loginByVerifyCodeCallback(apiResult: ApiResult<UserBean>) {
        if (apiResult.resultCode != ApiResultCodeEnum.SUCCESS.code ) {
            toast(apiResult.resultMsg )
            return
        }

        BaseApplication.instance!!.variable.userBean = apiResult.data
        //BaseApplication.getInstance().variable.yxUserBean = apiResult.data
        SPUtils.getInstance(this, Constants.PREF_FILENAME).writeString( Constants.PREF_USER, GsonUtils.gson!!.toJson(apiResult.data))
        //SPUtils.getInstance(this, Constants.PREF_FILENAME).writeString( Constants.PREF_YX_USER, GsonUtils.gson!!.toJson(apiResult.data))
        this.finish()
        //EventBus.getDefault().post(LoginSuccessEvent(apiResult.getData().getUserInfo()))
        //startActivity(MainActivity::class.java)
        //skipIntent<MainActivity>()

        CookieUtils.setWebViewCookie()
    }

    override fun showProgress(msg: String) {
        super.showProgress(msg)
        login_progress.setVisibility(View.VISIBLE)
    }

    override fun hideProgress() {
        super.hideProgress()
        login_progress.setVisibility(View.GONE)
    }


    override fun error(msg: String) {
        hideProgress()
        super.error(msg)
    }

    private fun clearLocalData(){
        SPUtils.getInstance(this,Constants.PREF_FILENAME).writeString( Constants.PREF_USER,"");

        BaseApplication.instance!!.variable.userBean=null

    }

    private fun clearCookie() {
        CookieUtils.clearWebViewCookie()
    }

//    fun checkRegCallback(apiResult: ApiResult<Map<String, Boolean>>) {
//        if (apiResult.resultCode !== ApiResultCodeEnum.SUCCESS.code) {
//            toast(apiResult.resultMsg )
//            return
//        }
//
//        isRegister = apiResult.data!!.get("regStatus")
//        //login_zhima_lay.setEnabled(  !isRegister );
//        login_zhima_lay.setVisibility(if (isRegister) View.GONE else View.VISIBLE)
//        login_line2.setVisibility(if (isRegister) View.GONE else View.VISIBLE)
//    }

    /**
     *
     */
    @NeedsPermission(Manifest.permission.RECEIVE_SMS, Manifest.permission.READ_SMS)
    fun checkReceiveSms() {
        getCode()
    }

    private fun registerSmsReceiver() {
        smsReceiver = SmsReceiver()
        smsReceiver!!.setSmsListener(this)
        val intentFilter = IntentFilter("android.provider.Telephony.SMS_RECEIVED")
        this.registerReceiver(smsReceiver, intentFilter)
    }

    override fun smsCallback(message: String) {
        if (TextUtils.isEmpty(message)) return
        val code = getDynamicPwd(message)
        if (TextUtils.isEmpty(code)) return

        runOnUiThread { login_code.setText( code) }
    }

    /**
     * 从字符串中截取连续4位数字组合 ([0-9]{4})截取4位数字 进行前后断言不能出现数字 用于从短信中获取动态密码
     *
     * @param content 短信内容
     * @return 截取得到的4位动态密码
     */
    fun getDynamicPwd(content: String): String {
        // 此正则表达式验证六位数字的短信验证码
        val pattern = Pattern.compile("(?<![0-9])([0-9]{4})(?![0-9])")
        val matcher = pattern.matcher(content)
        var dynamicPwd = ""
        while (matcher.find()) {
            dynamicPwd = matcher.group()
            Log.i("TAG", "getDynamicPwd: find pwd=$dynamicPwd")
        }
        return dynamicPwd
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        //LoginRegisterActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults)
        onRequestPermissionsResult( requestCode , grantResults)
    }
}
