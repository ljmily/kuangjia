package com.example.module_base.view

/**
 * Created by Xing
on 2018/7/14.
 */
interface BaseView {
    fun showLoading()
    fun hideLoading()
    fun onError(msg:String)
}