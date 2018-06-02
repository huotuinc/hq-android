package com.huotu.android.couponsleague.activity


import android.Manifest
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import android.view.WindowManager
import com.huotu.android.couponsleague.BuildConfig
import com.huotu.android.couponsleague.R
import com.huotu.android.couponsleague.base.BaseActivity
import com.huotu.android.couponsleague.base.BaseApplication
import com.huotu.android.couponsleague.bean.*
import com.huotu.android.couponsleague.mvp.contract.SplashContract
import com.huotu.android.couponsleague.utils.*
import com.huotu.android.phonerecycle.mvp.presenter.SplashPresenter
import kotlinx.android.synthetic.main.activity_splash.*
import permissions.dispatcher.*

@RuntimePermissions
class SplashActivity : BaseActivity<SplashContract.Presenter>() , SplashContract.View , View.OnClickListener{
    var presenter : SplashPresenter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        //setAnimation()

        checkPermissionWithPermissionCheck()
    }

    override fun setStatusBar() {
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }

    fun init() {
        layError.setOnClickListener(this)
        tvVersion.text = getString(R.string.app_name) + " v" + BuildConfig.VERSION_NAME

        var userString = SPUtils.getInstance(this, Constants.PREF_FILENAME)
                .readString(Constants.PREF_USER,"")
        if(userString !=null && !userString.trim().isEmpty()) {
            BaseApplication.instance!!.variable.userBean = GsonUtils.gson!!.fromJson(userString, UserBean::class.java)
        }

        presenter = SplashPresenter(this)
        //presenter!!.readCityData()
        presenter!!.initData()

    }

//    private fun setAnimation() {
//        val alphaAnimation = AlphaAnimation(0.1f,1.0f)
//        alphaAnimation.duration = 1000
//        val scaleAnimation = ScaleAnimation(0.1f,1.0f,0.1f,1.0f, ScaleAnimation.RELATIVE_TO_SELF,0.5f, ScaleAnimation.RELATIVE_TO_SELF,0.5f)
//        scaleAnimation.duration =1000
//        val animationSet = AnimationSet(true)
//        animationSet.addAnimation(alphaAnimation)
//        animationSet.addAnimation(scaleAnimation)
//        animationSet.duration = 1000
//        root_container.startAnimation(animationSet)
//        animationSet.setAnimationListener(object : Animation.AnimationListener{
//            override fun onAnimationRepeat(animation: Animation?) {
//
//            }
//
//            override fun onAnimationStart(animation: Animation?) {
//
//            }
//
//            override fun onAnimationEnd(animation: Animation?) {
//                newIntent<MainActivity>()
//                finish()
//            }
//        })
//    }


    @NeedsPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA)
    fun checkPermission() {
        init()
    }

//    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//
//        onRequestPermissionsResult(requestCode , grantResults)
//    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        onRequestPermissionsResult(requestCode , grantResults)
    }

    @OnShowRationale(Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE)
    fun showRationaleForCamera(request: PermissionRequest) {
        showRationaleDialog( "使用app，需要相关权限" , request)
    }

    private fun showRationaleDialog( message : String , request: PermissionRequest) {
        AlertDialog.Builder(this)
                .setPositiveButton("允许") { _, _ -> request.proceed() }
                .setNegativeButton("拒绝") { _, _ -> request.cancel() }
                .setCancelable(false)
                .setMessage(message)
                .show()
    }

    @OnPermissionDenied(Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE)
    fun onCameraDenied() {
        showToast("申请权限被拒绝，无法使用")
    }

    @OnNeverAskAgain(Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE)
    fun onCameraNeverAskAgain() {
        showToast( "不再询问权限" )
    }

    private fun gotoHome() {

        skipIntent<MainActivity>()
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.layError->{

            }
        }
    }


    override fun showProgress( msg:String){
        layError.visibility =View.GONE
    }

    override fun hideProgress(){

    }

    override fun error(err:String){
        gotoHome()
    }

//    override fun readCityDataCallback(list: ArrayList<Province>) {
//        BaseApplication.instance!!.variable.cityData =list
//        presenter!!.initData()
//    }

    override fun initDataCallback(result: ApiResult<InitDataBean>) {
        hideProgress()

//        if (result.data != null) {

            //BaseApplication.getInstance().setAbountUrl(result.getData().getAboutUrl())
            //BaseApplication.getInstance.setRegisterUrl(result.getData().getRegAgreementUrl())
            //BaseApplication.single.setCreUrl(result.getData().getCreditAuthUrl())
            //BaseApplication.single.setLoanProjectProcessUrl(result.getData().getLoanProjectProcessUrl())
            //BaseApplication.single.setFaceErrorValue(result.getData().getFaceErrorValue())
            //BaseApplication.single.setApplyListUrl(result.getData().getApplyListUrl())
            //BaseApplication.single.setCreditUrl(result.getData().getCreditUrl())
            //BaseApplication.single.setPublishListUrl(result.getData().getPublishListUrl())
//        }
        if(result.resultCode != ApiResultCodeEnum.SUCCESS.code){
            toast(result.resultMsg)
            gotoHome()
            return
        }

        if(result.data==null){
            gotoHome()
            return
        }

        //BaseApplication.instance!!.variable.faceErrorValue = result.data!!.faceErrorValue
        BaseApplication.instance!!.variable.initDataBean = result.data

        if (result.data != null && result.data!!.userInfo != null) {
            BaseApplication.instance!!.variable.userBean = result.data!!.userInfo
            SPUtils.getInstance(this ,Constants.PREF_FILENAME).writeString(Constants.PREF_USER, GsonUtils.gson!!.toJson(result.data!!.userInfo))

            CookieUtils.setWebViewCookie()

        }else{
            BaseApplication.instance!!.variable.userBean =null
            SPUtils.getInstance(this,Constants.PREF_FILENAME).writeString(Constants.PREF_USER , "")
        }


//        if (result.getData() != null && result.getData().getYxUserInfo() != null) {
//            BaseApplication.single.setYxUserBean(result.getData().getYxUserInfo())
//            PreferenceHelper.writeString(this, Constants.PREF_FILENAME, Constants.PREF_YX_USER, GsonUtil.getGson().toJson(result.getData().getYxUserInfo()))
//
//            CookieUtil.setWebViewCookie()
//        }




        gotoHome()
    }
}
