package com.example.module_base.common.net

import com.example.module_base.common.BaseConstant
import com.example.module_base.common.BaseConstant.KEY_SP_TOKEN
import com.example.module_base.common.BaseConstant.USER_ID
import com.example.module_base.data.UserData
import com.example.module_base.utils.AppPrefsUtils
import com.example.module_base.utils.PreferenceUtil
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Xing
on 2018/7/16.
 */
class RetrofitFactory private constructor() {
    companion object {
        val instance: RetrofitFactory by lazy { RetrofitFactory() }
    }

    private val retrofit: Retrofit
    private val interceptor: Interceptor

    init {
        interceptor = Interceptor { chain ->
            val token = AppPrefsUtils.getString(KEY_SP_TOKEN)
            val userId = AppPrefsUtils.getString(USER_ID)
            val request = chain.request().newBuilder()
                    .addHeader("Content-Type", "application/json")
                    .addHeader("charset", "utf-8")
                    .addHeader("token", token)
                    .addHeader("userId", userId)
                    .build()
            val response = chain.proceed(request)

            val newToken = response.headers().get("token")
            val newuserId = response.headers().get("userId")
            if (newToken.isNullOrEmpty().not()) {
                AppPrefsUtils.putString(KEY_SP_TOKEN, newToken!!)
                AppPrefsUtils.putString(USER_ID, newuserId!!)
            }
            response
        }
        retrofit = Retrofit.Builder()
                .baseUrl(BaseConstant.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(initClient())
                .build()
    }

    private fun initClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(initLogInterceptor())
                .connectTimeout(15, TimeUnit.MINUTES)
                .readTimeout(15, TimeUnit.MINUTES)
                .writeTimeout(15, TimeUnit.MINUTES)
                .build()
    }

    private fun initLogInterceptor(): Interceptor {
        val inter = HttpLoggingInterceptor()
        if (BaseConstant.DEBUG_ENABLE) {
            inter.level = HttpLoggingInterceptor.Level.BODY
        }
        return inter
    }

    fun <T> create(service: Class<T>): T {
        return retrofit.create(service)
    }

    private fun getUserId(): String {
        val userInfo = PreferenceUtil.find(BaseConstant.KEY_SP_USER, UserData::class.java)
                ?: return ""
        return userInfo.userId
    }
    /*private fun getUserId(): String {
        val userInfo = preferences.userId
                ?: return ""
        return userInfo
    }*/
}