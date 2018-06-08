package com.huotu.android.couponsleague.http

import android.os.Build
import com.huotu.android.couponsleague.BuildConfig
import com.huotu.android.couponsleague.base.BaseApplication
import com.huotu.android.couponsleague.bean.Constants
import com.huotu.android.couponsleague.bean.PlatTypeEnum
import com.huotu.android.couponsleague.utils.SignUtils
import okhttp3.*
import java.net.URLDecoder
import java.nio.charset.Charset

class HeaderIntercepter : Interceptor{

    override fun intercept(chain: Interceptor.Chain?): Response {
        var request : Request = chain!!.request()

        var headerParameter = getHeaderParameter()

        var newRequest = request
                .newBuilder()
                .addHeader("appVersion" , headerParameter.appVersion )
                .addHeader("hwid" , headerParameter.hwid )
                .addHeader("mobileType" , headerParameter.mobileType )
                .addHeader("osType",headerParameter.osType.toString())
                .addHeader("osVersion" , headerParameter.osVersion)
                .addHeader("userId" , headerParameter.userId.toString())
                .addHeader("userToken" , headerParameter.userToken )
                .addHeader("platType" , headerParameter.platType.toString())
                .build()

        newRequest = addSignParameter(newRequest)


        return chain.proceed(newRequest)
    }

    private fun getHeaderParameter(): HeaderParameter {

        val userid = if (BaseApplication.instance!!.variable.userBean == null) 0 else BaseApplication.instance!!.variable.userBean!!.UserId
        var userToken: String? = if (BaseApplication.instance!!.variable.userBean == null) "" else BaseApplication.instance!!.variable.userBean!!.Token
        userToken = if (userToken == null) "" else userToken
        var platType = BaseApplication.instance!!.variable.platType

        val headerParameter = HeaderParameter()

        headerParameter.appVersion = BuildConfig.VERSION_NAME
        headerParameter.userToken =userToken
        headerParameter.userId = userid
        headerParameter.hwid =  Build.ID
        headerParameter.mobileType = Build.MODEL
        headerParameter.osVersion = Build.VERSION.SDK_INT.toString()
        headerParameter.osType = Constants.OS_TYPE
        headerParameter.platType=platType

        return headerParameter
    }

    private fun addSignParameter(request: Request): Request? {
        return if (request.method() == "GET") {
            addGet(request)
        } else if (request.method() == "POST") {
            if (request.body() is MultipartBody) request else addPost(request)//当是 multipartbody 则不处理
        } else {
            null
        }
    }

    private fun addGet(request:Request):Request{
        var httpUrl :HttpUrl = request.url()
        var set = httpUrl.queryParameterNames()

        var maps = HashMap<String,String>()
        //maps.put("platType", getHeaderParameter(request , "platType"))
        //maps.put("userId" , getHeaderParameter(request, "userId"))
        maps.put("userToken" , getHeaderParameter(request, "userToken"))

        val timestamp = System.currentTimeMillis()
        var sign = ""
        maps.put("timestamp" , timestamp.toString())
        for (key in set) {
            var value = httpUrl.queryParameter(key)
            if(value==null) value=""
            maps.put(key , value)
        }

        sign = SignUtils.getInstance().sign(maps, Constants.APP_SECRET)

        maps["sign"] = sign


        val newHttpUrl = httpUrl.newBuilder()
                .addEncodedQueryParameter("timestamp", timestamp.toString())
                .addEncodedQueryParameter("sign", sign)
                .build()

        val newRequest = request.newBuilder()
                .url(newHttpUrl)
                .build()

        return newRequest

    }

    private fun getHeaderParameter(request: Request, key: String): String {
        var value: String? = ""
        if (request.headers() != null && request.headers().names() != null && request.headers().names().contains(key)) {
            value = request.header(key)
        }
        if(value==null) value=""
        return value
    }

    private fun addPost(request:Request):Request{

        var requestBody = request.body()
        var buffer = okio.Buffer()
        requestBody!!.writeTo(buffer)
        var charset = Charset.forName("UTF-8")
        var contentType = requestBody.contentType()
        if(contentType!=null){
            charset = contentType.charset(charset)
        }
        var paramsStr = buffer.readString(charset)
        var paramsStr2 = URLDecoder.decode(paramsStr , "utf-8")

        val maps = HashMap<String,String>()


        //maps.put("platType", getHeaderParameter(request, "platType"))
        //maps.put("userId", getHeaderParameter(request, "userId"))
        maps.put("userToken", getHeaderParameter(request, "userToken"))

        val pv = paramsStr2.split("&".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        for (p in pv) {
            val kv = p.split("=".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val k = kv[0]
            var v = ""
            if (kv.size > 1) {
                v = kv[1]
            }

            maps.put(k, v)
        }

        val timestamp = System.currentTimeMillis()
        maps.put("timestamp", timestamp.toString())
        val sign = SignUtils.getInstance().sign(maps, Constants.APP_SECRET)

        val requestBuilder = request.newBuilder()
        val formBodyBuilder = FormBody.Builder()
        formBodyBuilder.addEncoded("timestamp", timestamp.toString())
        formBodyBuilder.addEncoded("sign", sign)
        if (request.body() is FormBody) {
            val oldFormBody = request.body() as FormBody?
            for (i in 0 until oldFormBody!!.size()) {
                formBodyBuilder.addEncoded(oldFormBody.encodedName(i), oldFormBody.encodedValue(i))
            }
        }

        return requestBuilder.post(formBodyBuilder.build()).build()

    }
}