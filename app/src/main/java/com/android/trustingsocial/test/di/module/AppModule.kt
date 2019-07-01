package com.android.trustingsocial.test.di.module

import android.app.Application
import android.content.Context
import com.codding.test.startoverflowuser.di.qualifier.ApplicationContext
import com.codding.test.startoverflowuser.network.LoanApiInterface
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
class AppModule {

    @Provides
    @Singleton
    fun getLoanApiService (retrofit: Retrofit) : LoanApiInterface {
        return retrofit.create(LoanApiInterface::class.java)
    }


    @Provides
    @ApplicationContext
    fun provideApplicationContext(app: Application) : Context {
        return app.applicationContext
    }

}