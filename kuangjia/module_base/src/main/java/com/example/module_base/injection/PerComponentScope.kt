package com.example.module_base.injection

import kotlin.annotation.Retention
import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class PerComponentScope