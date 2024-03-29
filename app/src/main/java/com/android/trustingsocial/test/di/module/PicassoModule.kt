package com.android.trustingsocial.test.di.module

import android.content.Context
import com.codding.test.startoverflowuser.di.qualifier.ApplicationContext
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module(includes = [OkHttpClientModule::class])
class PicassoModule {

    @Provides
    @Singleton
    fun picasso(@ApplicationContext context: Context, okHttp3Downloader: OkHttp3Downloader) : Picasso {
        return Picasso.Builder(context)
            .downloader(okHttp3Downloader)
            .build()
    }

    @Provides
    @Singleton
    fun okHttp3Downloader(okHttpClient: OkHttpClient) : OkHttp3Downloader {
        return OkHttp3Downloader(okHttpClient)
    }


}