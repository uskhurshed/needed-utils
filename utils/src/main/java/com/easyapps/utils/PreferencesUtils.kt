package com.easyapps.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

object PreferencesUtils {

    private lateinit var prefs: SharedPreferences

    fun init(context: Context) {
        prefs = context.getSharedPreferences("main", Context.MODE_PRIVATE)
    }

    fun getPrefString(key: String, defValue: String?): String? {
        if (!prefIsInitialized()) return defValue
        return prefs.getString(key, defValue)
    }

    fun getPrefString(key: String, defValue: String): String {
        return if (!prefIsInitialized()) defValue
        else prefs.getString(key, defValue) ?: defValue
    }

    fun setPrefString(key: String, value: String?) {
        if (!prefIsInitialized()) return
        prefs.edit { putString(key, value) }
    }

    fun getPrefBool(key: String, defValue: Boolean): Boolean {
       return if (!prefIsInitialized()) defValue
        else  prefs.getBoolean(key, defValue)
    }

    fun setPrefBool(key: String, value: Boolean) {
        if (!prefIsInitialized()) return
        prefs.edit { putBoolean(key, value) }
    }

    fun getPrefInt(key: String, defValue: Int): Int {
        return  if (!prefIsInitialized()) return defValue
        else prefs.getInt(key, defValue)
    }

    fun setPrefInt(key: String, value: Int) {
        if (!prefIsInitialized()) return
        prefs.edit { putInt(key, value) }
    }

    private fun prefIsInitialized(): Boolean {
        return this::prefs.isInitialized
    }
}
