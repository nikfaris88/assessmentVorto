package com.example.assessmentvorto.module

import android.app.Application
import android.content.Context
import com.example.assessmentvorto.api.AppSchedulers
import com.example.assessmentvorto.providers.ResourceProvider
import com.example.assessmentvorto.providers.SchedulerProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    internal fun providesContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    internal fun provideSchedulers(): SchedulerProvider = AppSchedulers()

    @Provides
    @Singleton
    internal fun providesResources(context: Context) = ResourceProvider(context)

}