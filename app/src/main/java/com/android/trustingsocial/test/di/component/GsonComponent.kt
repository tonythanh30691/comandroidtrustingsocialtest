package com.android.trustingsocial.test.di.component

import com.android.trustingsocial.test.di.module.GsonModule
import com.android.trustingsocial.test.network.MockingInterceptor
import com.android.trustingsocial.test.viewmodal.MainViewModal
import dagger.Component
import javax.inject.Singleton

@Component(modules = [GsonModule::class])
@Singleton
interface GsonComponent  {
    fun inject(interceptor: MockingInterceptor)
    fun inject(viewModal: MainViewModal)
}