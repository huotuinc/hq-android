package com.huotu.android.couponsleague.http


import com.huotu.android.couponsleague.bean.*
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @POST("recycle/sys/init")
    fun init():Observable<ApiResult<InitDataBean>>


    /**
     * 根据手机号、邀请码和验证码注册用户
     */
    @POST("user/register")
    @FormUrlEncoded
    fun register(@Field("mobile") mobile:String,
                @Field("inviteCode") inviteCode:String,
                 @Field("verifyCode") verifyCode:String,
                 @Field("safeCode") safeCode:String="" ):Observable<ApiResult<UserBean>>


    /**
     * 根据手机号、验证码 实现用户登录逻辑
     * @param username  手机号
     * @param verifyCode  验证码
     * @param safeCode 安全码
     * @return
     */
    @POST("user/login")
    @FormUrlEncoded
    fun login(@Field("mobile") mobile: String,
                          @Field("verifyCode") verifyCode: String,
                          @Field("safeCode") safeCode: String="" ): Observable<ApiResult<UserBean>>


    /**
     * 发送短信验证码
     */
    @FormUrlEncoded
    @POST("user/sendVerifyCode")
    fun sendVerifyCode(@Field("mobile") mobile: String): Observable<ApiResult<Any>>


    /**
     * 添加收藏
     */
    @POST("user/favorite")
    @FormUrlEncoded
    fun favorite(@Field("goodsid") goodsid:Long ):Observable<ApiResult<Any>>


    /**
     * 我的收藏
     */
    @POST("user/favoriteList")
    @FormUrlEncoded
    fun favoriteList(@Field("pageIndex") pageIndex :Long , @Field("pageSize") pageSize :Int=Constants.PAGE_SIZE ):Observable<ApiResult<FavoriteBean>>

}