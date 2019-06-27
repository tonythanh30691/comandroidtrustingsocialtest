package com.android.trustingsocial.test.di.component

import android.app.Application
import com.android.trustingsocial.test.di.module.ActivityBuilder
import com.android.trustingsocial.test.di.module.AppModule
import com.android.trustingsocial.test.di.module.NetworkModule
import com.android.trustingsocial.test.di.module.PicassoModule
import com.codding.test.startoverflowuser.network.LoanApiInterface
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Component (modules = [NetworkModule::class, AppModule::class,
    AndroidInjectionModule::class, ActivityBuilder::class])
@Singleton
interface AppComponent {

    fun getLoanApiService() : LoanApiInterface

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: Application) : Builder
        fun build() : AppComponent
    }

    fun inject(app : Application)
}