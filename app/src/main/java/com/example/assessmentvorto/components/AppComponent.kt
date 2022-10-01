package com.example.assessmentvorto.components

import com.example.assessmentvorto.AssessmentVortoApplication
import com.example.assessmentvorto.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityModules::class,
        NetworkModule::class,
        ViewModelModule::class,
        AppModule::class,
        DomainModules::class,
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: AssessmentVortoApplication): Builder

        fun build(): AppComponent
    }

    fun inject(app: AssessmentVortoApplication)
}