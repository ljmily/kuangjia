package com.example.module_base.utils

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder

object GsonUtil {

    private fun getGsonObject(): Gson {
        return GsonBuilder().serializeNulls().create()
    }

    /**
     * 对象转Gson字符串
     *
     * @param object
     * @return
     */
    fun <T : Any> ser(`object`: T): String {
        val gson = getGsonObject()
        return gson.toJson(`object`)
    }

    /**
     * Gson字符串转可序列化对象
     *
     * @param json
     * @param clazz
     * @return
     */
    fun <T : Any> deser(json: String, clazz: Class<T>): T? {
        val gson = getGsonObject()

        var result: T? = null
        try {
            result = gson.fromJson(json, clazz)
        } catch (e: Exception) {
            result = null
            e.printStackTrace()
        }

        return result
    }

    fun <T : Any> deserBequiet(json: String, clazz: Class<T>): T? {
        val gson = getGsonObject()

        var result: T?
        try {
            result = gson.fromJson(json, clazz)
        } catch (e: Exception) {
            result = null
        }

        return result
    }

    fun <T> Json2Result(class1: Class<T>, s: String): T? {

        var result: T?
        try {
            result = Gson().fromJson(s, class1)
            Log.d(class1.toString() + "------Json Msg", s)
        } catch (e: Exception) {
            result = null
            Log.e(class1.toString() + "------Json Error", s)
        }

        return result
    }
}