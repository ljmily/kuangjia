package com.example.kuangjia.injection.module

import com.example.kuangjia.UserService
import com.example.kuangjia.UserServiceImpl
import dagger.Module
import dagger.Provides

/**
 * Created by Xing
 *on 2018/7/26.
 */
@Module
class UserModule {
    @Provides
    fun providesUserModule(service: UserServiceImpl): UserService {
        return service
    }
}