package com.android.trustingsocial.test.di.module

import com.android.trustingsocial.test.util.JsonHelper
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class GsonModule {

    @Provides
    @Singleton
    fun gson() : Gson {
        return GsonBuilder().create()
    }

    @Provides
    @Singleton
    fun jsonHelper(gson : Gson) : JsonHelper {
        return JsonHelper(gson)
    }

}