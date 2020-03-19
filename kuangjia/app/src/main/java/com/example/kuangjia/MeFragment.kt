package com.example.kuangjia

import android.util.Log
import android.widget.Toast
import com.example.kuangjia.injection.component.DaggerUserComponent
import com.example.kuangjia.injection.module.UserModule
import com.example.module_base.data.UserData
import com.example.module_base.fragment.BaseMvpFragment
import kotlinx.android.synthetic.main.me_fragment.*

@Suppress("DEPRECATION")
class MeFragment : BaseMvpFragment<BannerPresenter>(), HomeView {


    override fun getLayoutId(): Int {
        return R.layout.me_fragment
    }

    override fun initView() {
        mPresenter.ckrw("18801424016","Nir4PHR715WK2iZyA6RVsQ==")
    }


    override fun ckrw1(result: UserData) {
        Log.e("aaaa1",result.toString())
        ttt.text = result.toString()
    }

    override fun onNetChange(netWorkState: Boolean) {
        if (netWorkState){
            Toast.makeText(activity,"有网", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(activity,"没网", Toast.LENGTH_SHORT).show()
        }
    }
    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(activityComponent).userModule(UserModule()).build().inject(this)
        mPresenter.mView = this
    }
}