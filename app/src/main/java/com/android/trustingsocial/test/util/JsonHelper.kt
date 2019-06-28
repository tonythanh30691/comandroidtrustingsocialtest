package com.android.trustingsocial.test.util

import android.content.Context
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject

object JsonHelper {
    fun loadJsonFromRaw(context: Context, resourceId : Int) : String {
        return context.resources.openRawResource(resourceId).bufferedReader().use { it.readText() }
    }

    inline fun <reified T> convertJsonToClass(json : String) : T {
        var gson = GsonBuilder().create()
        return gson.fromJson(json, T::class.java)
    }

    fun <T> convertClassToJson(obj : T) : String {
        var gson = GsonBuilder().create()
        return gson.toJson(obj)
    }

    inline fun <reified T> convertClassFromJsonRaw(context: Context, resourceId : Int) : T {
        return(convertJsonToClass(loadJsonFromRaw(context, resourceId)))
    }
}