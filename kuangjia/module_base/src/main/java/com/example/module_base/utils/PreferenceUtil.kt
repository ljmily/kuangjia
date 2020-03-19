package com.example.module_base.utils

import android.annotation.SuppressLint
import com.example.module_base.common.BaseApplication
import java.io.Serializable
import java.util.ArrayList

@SuppressLint("StaticFieldLeak")
object PreferenceUtil {

    private val context = BaseApplication.context

    fun <T : Serializable> save(entity: T?, key: String): Boolean {
        if (entity == null) {
            return false
        }
        val prefFileName = entity.javaClass.name
        val sp = context.getSharedPreferences(prefFileName, 0)
        val et = sp.edit()
        val json = GsonUtil.ser(entity)
        et.putString(key, json)
        return et.commit()
    }

    fun <T : Serializable> findAll(clazz: Class<T>): List<T> {
        val prefFileName = clazz.name
        val sp = context.getSharedPreferences(prefFileName, 0)
        val values = sp.all as Map<*, *>

        val results = ArrayList<T>()

        if (values.isEmpty())
            return results

        val colles = values.values

        for (json in colles) {
            results.add(GsonUtil.deser(json as String, clazz)!!)
        }
        return results
    }

    fun <T : Serializable> find(key: String, clazz: Class<T>): T? {
        val prefFileName = clazz.name
        val sp = context.getSharedPreferences(prefFileName, 0)
        val json = sp.getString(key, null) ?: return null
        return GsonUtil.deser(json, clazz)
    }

    fun <T : Serializable> delete(key: String, clazz: Class<T>) {
        val prefFileName = clazz.name
        val sp = context.getSharedPreferences(prefFileName, 0)
        if (sp.contains(key)) {
            sp.edit().remove(key).apply()
        }
    }

    fun <T : Serializable> deleteAll(clazz: Class<T>) {
        val prefFileName = clazz.name
        val sp = context.getSharedPreferences(prefFileName, 0)
        sp.edit().clear().apply()
    }

}