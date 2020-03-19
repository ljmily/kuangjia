package com.example.module_base.fragment

import android.os.Bundle
import com.example.module_base.common.BaseApplication
import com.example.module_base.injection.component.ActivityComponent
import com.example.module_base.injection.component.DaggerActivityComponent
import com.example.module_base.injection.module.ActivityModule
import com.example.module_base.injection.module.LifecycleProviderModule
import com.example.module_base.presenter.BasePresenter
import com.example.module_base.utils.ToastUtils.error
import com.example.module_base.view.BaseView
import javax.inject.Inject

abstract class BaseMvpFragment<T : BasePresenter<*>> : BaseFragment(), BaseView {
    @Inject
    lateinit var mPresenter: T

    lateinit var activityComponent: ActivityComponent
    override fun showLoading() {

    }
    override fun hideLoading() {

    }
    override fun onError(msg: String) {
        error(msg)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActivityComponent();
        injectComponent()
    }

    abstract fun injectComponent()
    private fun initActivityComponent() {
        activityComponent = DaggerActivityComponent.builder()
                .appComponent((activity?.application as BaseApplication).appComponent)
                .activityModule(ActivityModule(activity!!))
                .lifecycleProviderModule(LifecycleProviderModule(this))
                .build()
    }
}