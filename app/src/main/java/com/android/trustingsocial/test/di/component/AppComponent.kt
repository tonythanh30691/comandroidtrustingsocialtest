package com.android.trustingsocial.test.di.component

import android.app.Application
import com.android.trustingsocial.test.LoanApplication
import com.android.trustingsocial.test.di.module.*
import com.codding.test.startoverflowuser.network.LoanApiInterface
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component (modules = [AppModule::class, ViewModalModule::class,
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

    fun inject(app : LoanApplication)
}