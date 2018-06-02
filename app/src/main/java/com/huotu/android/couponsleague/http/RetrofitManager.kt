package com.huotu.android.couponsleague.http

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.huotu.android.couponsleague.bean.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitManager {
    private var headerIntercepter:HeaderIntercepter?=null
    private var okHttpClient:OkHttpClient?=null
    private var gson : Gson?=null
    private var gsonConverterFactory : GsonConverterFactory?=null
    private var rxJava2CallAdapterFactory : RxJava2CallAdapterFactory?=null
    private var apiService : ApiService?=null
    private var retrofit:Retrofit?=null

    private fun provideHeaderIntercepter(): HeaderIntercepter? {
        if(headerIntercepter==null) headerIntercepter = HeaderIntercepter()
        return headerIntercepter
    }

    private fun provideOkHttpClient( headerIntercepter: HeaderIntercepter?):OkHttpClient?{

        if(okHttpClient==null) okHttpClient = OkHttpClient.Builder()
                .readTimeout(Constants.READ_TIMEOUT , TimeUnit.SECONDS)
                .connectTimeout(Constants.CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(Constants.WRITE_TIMEOUT , TimeUnit.SECONDS)
                .addInterceptor(headerIntercepter)
                .build()

        return okHttpClient
    }

    private fun provideGson(): Gson? {
        if(gson==null) gson = GsonBuilder().serializeNulls().create()
        return gson
    }

    private fun provideGsonConverter(gson: Gson?): GsonConverterFactory? {
        if(gsonConverterFactory==null ) gsonConverterFactory = GsonConverterFactory.create(gson)
        return gsonConverterFactory
    }

    private fun provideRxJava2CallAdapter(): RxJava2CallAdapterFactory? {
        if(rxJava2CallAdapterFactory==null) rxJava2CallAdapterFactory = RxJava2CallAdapterFactory.create()
        return rxJava2CallAdapterFactory
    }

    private fun provideRetroft( baseUrl : String = Constants.BASE_URL , okHttpClient: OkHttpClient?,
                       gsonConverterFactory: GsonConverterFactory?,
                       rxJava2CallAdapterFactory: RxJava2CallAdapterFactory?): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJava2CallAdapterFactory)
                .build()
    }




    private fun getRetrofit():Retrofit?{
        if(retrofit==null) retrofit =  provideRetroft( Constants.BASE_URL , provideOkHttpClient(provideHeaderIntercepter()) ,
               provideGsonConverter( provideGson() ) ,
               provideRxJava2CallAdapter() )
        return retrofit
    }


    fun getApiService(): ApiService? {
        if(apiService==null) apiService = getRetrofit()!!.create(ApiService::class.java)
        return apiService
    }

}