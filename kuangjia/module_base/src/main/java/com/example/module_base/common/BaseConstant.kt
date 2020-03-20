package com.example.module_base.common


object BaseConstant {

    var RELEASE_ENABLE = true   // true 线上, false 线下
    var DEBUG_ENABLE = true
    var BASE_URL = ""

    init {
        if (RELEASE_ENABLE) {
            DEBUG_ENABLE = true
            BASE_URL = "http://121.40.243.27:8080" //线上
        } else {
            DEBUG_ENABLE = true
             // BASE_URL = "http://192.168.2.69:8083" //线下
            BASE_URL = "http://192.168.2.108:8083" //线下
            //BASE_URL = "http://192.168.2.239:8083" //线下
        }
    }

    //SP表名
    const val TABLE_PREFS = "Jank"
    //Token Key
    const val KEY_SP_TOKEN = "token"

    const val USER_ID = "USER_ID"
    //用户
    const val KEY_SP_USER = "sp_user"

    //pushId
    const val KET_PUSH_ID = "KET_PUSH_ID"

    const val KET_ORDER_ID="KET_ORDER_ID"
    const val FINDORDER = "FINDORDER"
}