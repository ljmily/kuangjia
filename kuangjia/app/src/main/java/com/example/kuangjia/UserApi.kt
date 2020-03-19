package com.example.kuangjia

import com.example.module_base.data.BaseResp
import com.example.module_base.data.UserData
import io.reactivex.Observable
import retrofit2.http.*

/**
 * Created by Xing
on 2018/7/16.
 */
interface UserApi {


    //登陆
    @FormUrlEncoded
    @POST("/openApi/login")
    fun login(@Field("phone") phone: String,
              @Field("password") password: String): Observable<BaseResp<UserData>>


}