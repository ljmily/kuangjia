package com.example.module_base.injection.component

import android.app.Activity
import android.content.Context
import com.example.module_base.injection.ActivityScope
import com.example.module_base.injection.module.ActivityModule
import com.example.module_base.injection.module.LifecycleProviderModule
import com.trello.rxlifecycle2.LifecycleProvider
import dagger.Component

@ActivityScope
@Component(dependencies = [AppComponent::class], modules = [ActivityModule::class, LifecycleProviderModule::class])
interface ActivityComponent {
    fun activity(): Activity
    fun context(): Context
    fun lifecycleProvider(): LifecycleProvider<*>
}