package com.example.module_base.data

/**
 * Created by Xing
on 2018/7/16.
 */
class BaseResp<out T>(val resultCode: String, val resultMsg: String, val data: T)