package com.android.trustingsocial.test

import android.app.Activity
import android.app.Application
import com.android.trustingsocial.test.di.component.AppComponent
import com.android.trustingsocial.test.di.component.DaggerAppComponent
import com.squareup.picasso.Picasso
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import javax.inject.Inject

class LoanApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>
    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())

        appComponent = DaggerAppComponent.builder().application(this).build()
        appComponent.inject(this)
        Picasso.setSingletonInstance(appComponent.getPicasso())
    }

    fun getAppComponent() : AppComponent {
        return appComponent
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityInjector
    }

}