package com.android.trustingsocial.test.util

import android.content.Context
import com.google.gson.Gson
import javax.inject.Inject

class JsonHelper @Inject constructor(var gson: Gson) {

    fun loadJsonFromRaw(context: Context, resourceId : Int) : String {
        return context.resources.openRawResource(resourceId).bufferedReader().use { it.readText() }
    }

    inline fun <reified T> convertJsonToClass(json : String) : T {
        return gson.fromJson(json, T::class.java)
    }

    fun <T> convertClassToJson(obj : T) : String {
        return gson.toJson(obj)
    }

    inline fun <reified T> convertClassFromJsonRaw(context: Context, resourceId : Int) : T {
        return(convertJsonToClass(loadJsonFromRaw(context, resourceId)))
    }

}