package com.example.module_base.common

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import cn.jpush.im.android.api.JMessageClient
import com.alibaba.android.arouter.launcher.ARouter
import com.example.module_base.R
import com.example.module_base.event.NotificationClickEventReceiver
import com.example.module_base.injection.component.AppComponent
import com.example.module_base.injection.component.DaggerAppComponent
import com.example.module_base.injection.module.AppModule
import com.helper.loadviewhelper.load.LoadViewHelper

open class BaseApplication : Application() {

    lateinit var appComponent: AppComponent
    private val wxAppID: String = "wxf4e0fa774c1378e0"

    override fun onCreate() {
        super.onCreate()



        /*if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);*/
        //获取APP ID并将以下代码复制到项目Application类onCreate()中，Bugly会为自动检测环境并完成配置：(就一行)
       // CrashReport.initCrashReport(getApplicationContext(), "131430ea58", false);
        initAppInjection()
//        initWx()
        initRouter()
        initJG()
        initLoadingHelper()
        context = this
        //glide Tag冲突问题
        //ViewTarget.setTagId(R.id.glide_tag)
        //注册Notification点击的接收器
        NotificationClickEventReceiver(this)
        // crashHandler
//        CrashHandler.init(this)

    }

    private fun initLoadingHelper() {
        LoadViewHelper.getBuilder()
                .setLoadEmpty(R.layout.load_empty)
                .setLoadError(R.layout.load_error)
                .setLoadIng(R.layout.load_ing)
    }

    private fun initJG() {
        JMessageClient.init(applicationContext, true)
        JMessageClient.setDebugMode(true)
        //设置Notification的模式
        JMessageClient.setNotificationFlag(JMessageClient.FLAG_NOTIFY_WITH_SOUND or JMessageClient.FLAG_NOTIFY_WITH_LED or JMessageClient.FLAG_NOTIFY_WITH_VIBRATE)
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    /**
     * 微信分享的初始化
     */
    private fun initWx() {
       // wxapi = WXAPIFactory.createWXAPI(this, wxAppID, true)
        //wxapi.registerApp(wxAppID)
    }

    private fun initRouter() {
        //ARouter初始化
        ARouter.openLog()    // 打印日志
        ARouter.openDebug()
        ARouter.init(this)
    }

    private fun initAppInjection() {
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }


    /*
     * 全局伴生对象
     */
    companion object {
        lateinit var context: BaseApplication
        const val CONV_TITLE = "conv_title"
        const val TARGET_ID = "targetId"
        const val GROUP_ID = "groupId"
        const val TARGET_APP_KEY = "targetAppKey"

        const val path_chatActivity = "/Chat/ChatActivity"
    }
}