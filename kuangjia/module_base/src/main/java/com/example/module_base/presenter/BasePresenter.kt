package com.example.module_base.presenter

import android.content.Context
import com.example.module_base.utils.NetWorkUtils
import com.example.module_base.view.BaseView
import com.trello.rxlifecycle2.LifecycleProvider
import javax.inject.Inject

/**
 * Created by Xing
on 2018/7/14.
 */
open class BasePresenter<T : BaseView> {
    lateinit var mView: T

    @Inject
    lateinit var lifecycleProvider: LifecycleProvider<*>

    @Inject
    lateinit var context: Context

    fun checkNetwork(): Boolean {
        if (NetWorkUtils.isNetWorkAvailable(context))
            return true
        mView.onError("网络不可用,请稍后重试")

        return false
    }
}