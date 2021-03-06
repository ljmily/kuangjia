package com.example.module_base.injection.component

import android.content.Context
import com.example.module_base.injection.module.AppModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent{
    fun context():Context
}