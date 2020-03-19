package com.example.kuangjia

import com.example.module_base.data.UserData
import com.example.module_base.ext.execute
import com.example.module_base.presenter.BasePresenter
import com.example.module_base.rx.BaseSubscriber
import javax.inject.Inject

class BannerPresenter @Inject constructor(): BasePresenter<HomeView>() {

    @Inject
    lateinit var homeService: UserServiceImpl

    fun ckrw( phone :String,password :String) {
        homeService.login(phone, password)
            .execute(object : BaseSubscriber<UserData>(mView){
                override fun onNext(t: UserData) {
                    mView.ckrw1(t)
                }
            },lifecycleProvider)
    }
}