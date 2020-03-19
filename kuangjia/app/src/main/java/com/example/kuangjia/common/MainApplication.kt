package com.example.kuangjia.common

import com.example.module_base.common.BaseApplication


class MainApplication: BaseApplication() {

    override fun onCreate() {
        super.onCreate()

        //极光推送初始化
       /* JPushInterface.setDebugMode(true)
        JPushInterface.init(this)*/
    }
}