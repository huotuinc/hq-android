package com.huotu.android.couponsleague.http


import com.huotu.android.couponsleague.bean.ApiResult
import com.huotu.android.couponsleague.bean.InitDataBean
import com.huotu.android.couponsleague.bean.UserBean
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @POST("recycle/sys/init")
    fun init():Observable<ApiResult<InitDataBean>>


    /**
     * 根据手机号、验证码 实现用户登录注册逻辑
     * @param username  手机号
     * @param verifyCode  验证码
     * @param inviter 邀请者ID
     * @param zmfScore 预估芝麻分， 1:550以下 2:550~600 3:600~650 4:650以上
     * @return
     */
    @POST("user/loginByVerifyCode")
    @FormUrlEncoded
    fun loginByVerifyCode(@Field("username") username: String,
                          @Field("verifyCode") verifyCode: String,
            //@Field("inviter") inviter: Int=0,
                          @Field("zmfScore") zmfScore: Int=0): Observable<ApiResult<UserBean>>


    /**
     * 发送短信验证码
     */
    @FormUrlEncoded
    @POST("user/sendVerifyCode")
    fun sendVerifyCode(@Field("mobile") mobile: String): Observable<ApiResult<Any>>



}