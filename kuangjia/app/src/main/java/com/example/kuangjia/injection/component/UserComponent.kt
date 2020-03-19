package com.example.kuangjia.injection.component

import com.example.kuangjia.MainActivity
import com.example.kuangjia.MeFragment
import com.example.kuangjia.injection.module.UserModule
import com.example.module_base.injection.PerComponentScope
import com.example.module_base.injection.component.ActivityComponent
import dagger.Component

/**
 * Created by Xing
on 2018/7/26.
 */
@PerComponentScope
@Component(dependencies = [ActivityComponent::class], modules = [(UserModule::class)])
interface UserComponent {
    fun inject(activity: MainActivity)
    fun inject(fragment: MeFragment)

}

