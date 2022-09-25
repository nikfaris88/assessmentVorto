package com.example.assessmentvorto.module

import com.example.assessmentvorto.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModules {

    @ContributesAndroidInjector
    internal abstract fun contributeMainActivity(): MainActivity
}