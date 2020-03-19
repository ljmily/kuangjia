package com.example.module_base.injection.module

import android.app.Activity
import com.example.module_base.injection.ActivityScope
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: Activity) {
    @ActivityScope
    @Provides
    fun providesActivity(): Activity {
        return activity
    }
}