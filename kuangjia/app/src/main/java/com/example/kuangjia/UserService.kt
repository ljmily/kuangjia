package com.example.kuangjia

import com.example.module_base.data.UserData
import io.reactivex.Observable


/**
 * Created by Xing
on 2018/7/16.
 */
interface UserService {


    fun login(phone: String, password: String): Observable<UserData>

}