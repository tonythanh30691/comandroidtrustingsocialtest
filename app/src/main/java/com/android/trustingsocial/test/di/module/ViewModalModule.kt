package com.android.trustingsocial.test.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.trustingsocial.test.di.key.ViewModalKey
import com.android.trustingsocial.test.viewmodal.MainViewModal
import com.android.trustingsocial.test.viewmodal.ViewModalFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [GsonModule::class])
abstract class ViewModalModule {

    @Binds
    abstract fun bindViewModalFactory(viewModalFactory: ViewModalFactory) : ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModalKey(MainViewModal::class)
    abstract fun bindMainViewModal(mainViewModal: MainViewModal) : ViewModel
}