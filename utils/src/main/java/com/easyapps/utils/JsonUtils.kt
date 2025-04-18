package com.easyapps.utils

import org.json.JSONArray
import org.json.JSONObject

object JsonUtils {

    fun JSONObject.string(key: String, defaultValue: String = ""): String {
        return if (isNull(key)) defaultValue
        else optString(key)
    }


    fun JSONObject.stringOrNull(name:String): String? {
        return if (isNull(name)) null
        else optString(name)
    }

    fun JSONObject.int(key: String, defaultValue: Int = 0): Int {
        return optInt(key,defaultValue)
    }

    fun JSONObject.intOrNull(key: String): Int? {
        return if (isNull(key)) null
        else optInt(key)
    }

    fun JSONObject.double(key: String, defaultValue: Double = 0.0): Double {
        return optDouble(key,defaultValue)
    }

    fun JSONObject.boolean(key: String, defaultValue: Boolean = false): Boolean {
        return optBoolean(key,defaultValue)
    }

    fun JSONObject.long(key: String, defaultValue: Long = 0L): Long {
        return optLong(key,defaultValue)
    }

    fun JSONObject.jsonObject(key: String): JSONObject {
        return optJSONObject(key) ?: JSONObject()
    }

    fun JSONArray.jsonObject(index: Int): JSONObject {
        return optJSONObject(index) ?: JSONObject()
    }

    fun JSONObject.jsonArray(key: String): JSONArray {
        return optJSONArray(key) ?: JSONArray()
    }

    fun JSONArray.jsonArray(index: Int): JSONArray {
        return optJSONArray(index) ?: JSONArray()
    }

    fun JSONArray.string(index: Int, defaultValue: String = ""): String {
        return if (isNull(index)) defaultValue
        else optString(index)
    }

    fun JSONArray.stringOrNull(index: Int): String? {
        return if (isNull(index)) null
        else optString(index)
    }

    fun JSONArray.int(index: Int, defaultValue: Int = 0): Int {
        return optInt(index,defaultValue)
    }

    fun JSONArray.double(index: Int, defaultValue: Double = 0.0): Double {
        return optDouble(index,defaultValue)
    }

    fun JSONArray.boolean(index: Int, defaultValue: Boolean = false): Boolean {
        return optBoolean(index,defaultValue)
    }

    fun JSONArray.long(index: Int, defaultValue: Long = 0L): Long {
        return optLong(index,defaultValue)
    }

    fun String?.toJSONObject(): JSONObject {
        return try {
            JSONObject(this ?: "{}")
        } catch (e: Exception) {
            JSONObject()
        }
    }

    fun Any?.asJSONObject():JSONObject{
        return try {
            (this as JSONObject)
        } catch (e: Exception) {
            JSONObject()
        }
    }

    fun Any?.asJSONObjectOrNull():JSONObject?{
        return try {
            (this as JSONObject)
        } catch (e: Exception) {
            null
        }
    }

    fun Any?.asJSONArray():JSONArray{
        return try {
            (this as JSONArray)
        } catch (e: Exception) {
            JSONArray()
        }
    }

    fun Any?.asJSONArrayOrNull():JSONArray?{
        return try {
            (this as JSONArray)
        } catch (e: Exception) {
            null
        }
    }

    fun String?.toJSONArray(): JSONArray {
        return try {
            JSONArray(this ?: "[]")
        } catch (e: Exception) {
            JSONArray()
        }
    }

    fun String?.toJSONObjectOrNull(): JSONObject? {
        if (isNullOrEmpty()) return null
        return try {
            JSONObject(this)
        } catch (e: Exception) {
            null
        }
    }

    fun String?.toJSONArrayOrNull(): JSONArray? {
        if (isNullOrEmpty()) return null
        return try {
            JSONArray(this)
        } catch (e: Exception) {
            null
        }
    }


}