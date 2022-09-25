package com.example.assessmentvorto

import android.app.Application
import androidx.lifecycle.LifecycleObserver
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject


class AssessmentVortoApplication : Application(), HasAndroidInjector, LifecycleObserver {

    companion object{
        @get:Synchronized
        lateinit var instance : AssessmentVortoApplication
    }

    @Inject
    lateinit var activityDispatcher: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> {
        return activityDispatcher
    }

    override fun onCreate() {
        super.onCreate()

        instance = this

        DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this)

    }

}