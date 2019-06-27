package com.android.trustingsocial.test.di.module

import com.android.trustingsocial.test.ui.MainActivity
import com.codding.test.startoverflowuser.di.scope.MainActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder  {
    @MainActivityScope
    @ContributesAndroidInjector
    abstract fun bindMainActivity() : MainActivity
}