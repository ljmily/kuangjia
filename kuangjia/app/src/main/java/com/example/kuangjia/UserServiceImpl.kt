package com.example.kuangjia

import com.example.module_base.data.UserData
import com.example.module_base.ext.convert
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by Xing
on 2018/7/16.
 */
class UserServiceImpl @Inject constructor() : UserService {

    @Inject
    lateinit var repository: UserRepository

    override fun login(phone: String, password: String): Observable<UserData> {
        return repository.login(phone, password).convert()
    }


}