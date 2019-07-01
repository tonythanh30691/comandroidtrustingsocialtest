package com.android.trustingsocial.test.di.module

import com.android.trustingsocial.test.util.ApiConstant
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [OkHttpClientModule::class, GsonModule::class])
class NetworkModule {

    @Provides
    @Singleton
    fun retrofix(okHttpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory,
                 coroutineCallAdapterFactory: CoroutineCallAdapterFactory) : Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(ApiConstant.API_BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(coroutineCallAdapterFactory)
            .build()
    }


    @Provides
    @Singleton
    fun gsonConverterFactory(gson: Gson) : GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }

    @Provides
    @Singleton
    fun coroutineCallAdapterFactory() : CoroutineCallAdapterFactory {
        return CoroutineCallAdapterFactory()
    }
}