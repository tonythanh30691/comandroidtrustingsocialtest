package com.android.trustingsocial.test.di.module

import android.content.Context
import com.android.trustingsocial.test.network.MockingInterceptor
import com.android.trustingsocial.test.util.ApiConstant
import com.android.trustingsocial.test.util.CacheConstant
import com.codding.test.startoverflowuser.di.qualifier.ApplicationContext
import com.codding.test.startoverflowuser.di.qualifier.LoggingIntercepter
import com.codding.test.startoverflowuser.di.qualifier.MockIntercepter
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber
import java.io.File
import java.util.concurrent.TimeUnit

@Module(includes = [AppModule::class])
class OkHttpClientModule {

    @Provides
    fun okHttpClient(cache: Cache, @LoggingIntercepter httpLoggingInterceptor: Interceptor,
                     @MockIntercepter applicationIntercepter : Interceptor) : OkHttpClient {
        return OkHttpClient()
            .newBuilder()
            .cache(cache)
            .connectTimeout(ApiConstant.API_CONNECTION_TIMEOUT , TimeUnit.SECONDS)
            .readTimeout(ApiConstant.API_CONNECTION_TIMEOUT , TimeUnit.SECONDS)
            .addInterceptor(applicationIntercepter)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    fun cache(file : File) : Cache {
        return Cache(file, CacheConstant.CACHE_SIZE_IN_MB * 1000 * 1000)
    }

    @Provides
    fun file(@ApplicationContext context: Context) : File {
        var file = File(context.cacheDir, CacheConstant.CACHE_FOLDER_NAME)
        file.mkdir()
        return file
    }

    @Provides
    @LoggingIntercepter
    fun httpLoggingInterceptor() : Interceptor {
        var logger = HttpLoggingInterceptor { message -> Timber.d(message)  }
        logger.level = HttpLoggingInterceptor.Level.BODY
        return logger
    }

    @Provides
    @MockIntercepter
    fun mockingIntercepter(@ApplicationContext context: Context) : Interceptor {
        return MockingInterceptor(context)
    }
}