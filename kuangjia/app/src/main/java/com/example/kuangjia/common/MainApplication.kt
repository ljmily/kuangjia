package com.example.kuangjia.common

import cn.jpush.android.api.JPushInterface
import com.example.module_base.common.BaseApplication

class MainApplication: BaseApplication() {

    override fun onCreate() {
        super.onCreate()

        //极光推送初始化
        //初始化sdk
        JPushInterface.setDebugMode(true);//正式版的时候设置false，关闭调试
        JPushInterface.init(this);
        //添加tag标签，发送消息的之后就可以指定tag标签来发送了
        //Set<String> set = new HashSet<>();
        //set.add("andfixdemo");//名字任意，可多添加几个
        //JPushInterface.setTags(this, set, null);//设置标签
    }
}