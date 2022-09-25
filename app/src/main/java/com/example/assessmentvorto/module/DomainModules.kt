package com.example.assessmentvorto.module

import com.example.assessmentvorto.providers.SchedulerProvider
import com.example.assessmentvorto.repo.MainRepository
import com.example.assessmentvorto.usecase.TransactionSearchUseCase
import dagger.Module
import dagger.Provides


@Module
class DomainModules {
    @Provides
    fun providesMainRepository(schedulers: SchedulerProvider, mainRepository: MainRepository) =
        TransactionSearchUseCase(schedulers, mainRepository)

}