package com.example.module_base.activity

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import com.example.module_base.common.BaseApplication
import com.example.module_base.injection.component.ActivityComponent
import com.example.module_base.injection.component.DaggerActivityComponent
import com.example.module_base.injection.module.ActivityModule
import com.example.module_base.injection.module.LifecycleProviderModule
import com.example.module_base.presenter.BasePresenter
import com.example.module_base.utils.ProgressLoading
import com.example.module_base.utils.ToastUtils
import com.example.module_base.view.BaseView
import javax.inject.Inject

/**
 * Created by Xing
on 2018/7/14.
 */
abstract class BaseMvpActivity<T : BasePresenter<*>> : BaseActivity(), BaseView {
    override fun hideLoading() {
        mLoading.hideLoading()
    }

    override fun onError(msg: String) {
        ToastUtils.error(msg)
    }


    override fun showLoading() {
        mLoading.showLoading()
    }

    @Inject
    lateinit var mPresenter: T

    lateinit var activityComponent: ActivityComponent

    open lateinit var mLoading: ProgressLoading

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActivityComponent()
        injectComponent()

        mLoading= ProgressLoading.create(this)
    }

    abstract fun injectComponent()


    private fun initActivityComponent() {
        activityComponent = DaggerActivityComponent.builder()
                .activityModule(ActivityModule(this))
                .appComponent((application as BaseApplication).appComponent)
                .lifecycleProviderModule(LifecycleProviderModule(this))
                .build()
    }


}