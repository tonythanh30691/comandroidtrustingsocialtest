package com.android.trustingsocial.test.di.module

import android.app.Application
import android.content.Context
import com.codding.test.startoverflowuser.di.qualifier.ApplicationContext
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    @ApplicationContext
    fun provideApplicationContext(app: Application) : Context {
        return app.applicationContext
    }

}