package com.huotu.android.couponsleague.activity

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.net.http.SslError
import android.os.Build
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.webkit.*
import com.huotu.android.couponsleague.R
import com.huotu.android.couponsleague.base.BaseActivity
import com.huotu.android.couponsleague.base.BaseApplication
import com.huotu.android.couponsleague.bean.Constants
import com.huotu.android.couponsleague.mvp.IPresenter
import com.huotu.android.couponsleague.utils.UrlInterceptor
import com.huotu.android.couponsleague.widget.TipAlertDialog
import kotlinx.android.synthetic.main.activity_web.*

class WebActivity : BaseActivity<IPresenter>() ,View.OnClickListener{
    var url:String?=""
    var mUploadMessage: ValueCallback<Uri>?=null
    val FILECHOOSER_RESULTCODE = 1
    val urlIntercepter : UrlInterceptor = UrlInterceptor(this)
    var isCanBack :Boolean=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)

        url = intent.extras.getString(Constants.INTENT_URL)
        isCanBack = intent.extras.getBoolean(Constants.INTENT_CANBACK , false)

        toolbar.setNavigationOnClickListener(this)

        loadPage()
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.toolbar->{

                if(isCanBack&& webView.canGoBack()){
                    webView.goBack()
                    return
                }

                finish()
            }
        }
    }

    private fun loadPage() {
        webView.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY)
        webView.setVerticalScrollBarEnabled(false)
        webView.setHorizontalScrollBarEnabled(false)
        webView.setClickable(true)
        webView.getSettings().setUseWideViewPort(true)
        //是否需要避免页面放大缩小操作
        //viewPage.getSettings().setSupportZoom(true);
        //viewPage.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setJavaScriptEnabled(true)
        webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT)
        webView.getSettings().setSaveFormData(true)
        webView.getSettings().setAllowFileAccess(true)
        webView.getSettings().setLoadWithOverviewMode(false)
        //viewPage.getSettings().setSavePassword(true);
        webView.getSettings().setLoadsImagesAutomatically(true)
        webView.getSettings().setDomStorageEnabled(true)
        webView.getSettings().setAppCacheEnabled(true)
        webView.getSettings().setDatabaseEnabled(true)
        val dir = BaseApplication.instance!!.getDir("database", Context.MODE_PRIVATE).getPath()
        //webView.getSettings().setGeolocationDatabasePath(dir);
        webView.getSettings().setGeolocationEnabled(true)
        //webView.addJavascriptInterface(this, "android");
        val appCacheDir = BaseApplication.instance!!.getDir("cache", Context.MODE_PRIVATE).getPath()
        webView.getSettings().setAppCachePath(appCacheDir)

        //        if(BuildConfig.DEBUG && Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT ){
        //            WebView.setWebContentsDebuggingEnabled(true);
        //        }
        // android 5.0以上默认不支持Mixed Content
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE)
        }

        //signHeader( viewPage );

        webView.loadUrl(url)

        webView.webViewClient =
                object : WebViewClient() {

                    override fun shouldOverrideUrlLoading(view: WebView, url: String?): Boolean {
                        if (url != null && url.startsWith("tel:")) {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                            startActivity(intent)
                            return true
                        }

                        return if (urlIntercepter.shouldOverrideUrlLoading(view, url)) {
                            true
                        } else {
                            super.shouldOverrideUrlLoading(view, url)
                        }

//                        return super.shouldOverrideUrlLoading(view, url);
                    }

//                    override fun shouldInterceptRequest(view: WebView?, url: String?): WebResourceResponse {
//                        return super.shouldInterceptRequest(view, url)
//                    }

//                    override fun shouldInterceptRequest(view: WebView?, request: WebResourceRequest?): WebResourceResponse {
//                        return super.shouldInterceptRequest(view, request)
//                    }

                    override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
                        if (view.url != null && view.url.startsWith("tel:")) {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(view.url))
                            startActivity(intent)
                            return true
                        }

                        return if (urlIntercepter.shouldOverrideUrlLoading(view, url)) {
                            true
                        } else {
                            super.shouldOverrideUrlLoading(view, request)
                        }

                        //return super.shouldOverrideUrlLoading(view, request)
                    }

                    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                        super.onPageStarted(view, url, favicon)
                    }

                    override fun doUpdateVisitedHistory(view: WebView?, url: String?, isReload: Boolean) {
                        super.doUpdateVisitedHistory(view, url, isReload)

                    }

                    override fun onPageFinished(view: WebView?, url: String?) {
                        super.onPageFinished(view, url)
                        if (toolbar == null) return
                        toolbar.title =view?.title
                    }


                    override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
                        super.onReceivedError(view, request, error)
                        if (web_progressBar == null) return
                        web_progressBar.visibility = View.GONE
                    }


                    override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
                        super.onReceivedSslError(view, handler, error)
                        //当访问https网页，发生错误时，继续浏览网页
                        handler?.proceed()
                    }

                }


        webView.webChromeClient = object : WebChromeClient() {
            override fun onReceivedTitle(view: WebView, title: String?) {
                super.onReceivedTitle(view, title)
                if (toolbar == null) {
                    return
                }
                if (title == null) {
                    return
                }
                toolbar.title =title
            }

            override fun onProgressChanged(view: WebView, newProgress: Int) {
                if ( web_progressBar == null) return
                if (100 == newProgress) {
                    web_progressBar.visibility =View.GONE
                } else {
                    if (web_progressBar.visibility == View.GONE) web_progressBar.visibility = View.VISIBLE
                    web_progressBar.progress = newProgress
                }
                super.onProgressChanged(view, newProgress)
            }

            fun openFileChooser(uploadMsg: ValueCallback<Uri>) {
                mUploadMessage = uploadMsg
                val i = Intent(Intent.ACTION_GET_CONTENT)
                i.addCategory(Intent.CATEGORY_OPENABLE)
                i.type = "*/*"
                this@WebActivity.startActivityForResult(Intent.createChooser(i, "File Chooser"), FILECHOOSER_RESULTCODE)
            }

            fun openFileChooser(uploadMsg: ValueCallback<Uri>, acceptType: String) {
                openFileChooser(uploadMsg)
            }

            //For Android 4.1
            fun openFileChooser(uploadMsg: ValueCallback<Uri>, acceptType: String, capture: String) {
                openFileChooser(uploadMsg)
            }

            override fun onShowFileChooser(webView: WebView, filePathCallback: ValueCallback<Array<Uri>>, fileChooserParams: WebChromeClient.FileChooserParams): Boolean {
                return super.onShowFileChooser(webView, filePathCallback, fileChooserParams)
            }

            override fun onGeolocationPermissionsShowPrompt(origin: String, callback: GeolocationPermissions.Callback) {
                callback.invoke(origin, true, false)
                super.onGeolocationPermissionsShowPrompt(origin, callback)
            }

            override fun onJsConfirm(view: WebView?, url: String, message: String, result: JsResult): Boolean {
                //return super.onJsConfirm(view, url, message, result);
                if (view == null || view.context == null) return true

                val tipAlertDialog = TipAlertDialog(view.context, false)
                tipAlertDialog.show("询问", message, View.OnClickListener {
                    tipAlertDialog.dismiss()
                    result.cancel()
                }, View.OnClickListener {
                    tipAlertDialog.dismiss()
                    result.confirm()
                })

                return true
            }
        }


    }

    private fun dealBack() : Boolean {

        if(isCanBack&& webView.canGoBack()){
            webView.goBack()
            return true
        }
        return false
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
//        if(keyCode == KeyEvent.KEYCODE_BACK && isCanBack && webView.canGoBack()){
//            webView.goBack()
//            return true
//        }

        if(keyCode==KeyEvent.KEYCODE_BACK) {

            if (dealBack()) return true
        }


        return super.onKeyDown(keyCode, event)
    }
}
