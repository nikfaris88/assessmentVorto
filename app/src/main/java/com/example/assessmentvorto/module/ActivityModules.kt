package com.example.assessmentvorto.module

import com.example.assessmentvorto.ui.MainActivity
import com.example.assessmentvorto.ui.MapsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModules {

    @ContributesAndroidInjector
    internal abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    internal abstract fun contributeMapsActivity(): MapsActivity
}