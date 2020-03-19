package com.example.kuangjia

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.module_base.data.UserData
import com.example.module_base.fragment.BaseMvpFragment
import com.example.module_base.view.BaseView

class WdFragment : BaseMvpFragment<BannerPresenter>(),HomeView {
    override fun injectComponent() {

    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {

    }

    override fun ckrw1(result: UserData) {

    }

}
