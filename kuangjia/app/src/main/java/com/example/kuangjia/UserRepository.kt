package com.example.kuangjia

import com.example.module_base.common.net.RetrofitFactory
import com.example.module_base.data.BaseResp
import com.example.module_base.data.UserData
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by Xing
on 2018/7/16.
 */
class UserRepository @Inject constructor() {


    fun login(phone: String, password: String): Observable<BaseResp<UserData>> {
        return RetrofitFactory.instance.create(UserApi::class.java).login(phone, password)
    }

}