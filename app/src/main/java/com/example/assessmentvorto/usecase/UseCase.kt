package com.example.assessmentvorto.usecase

import com.example.assessmentvorto.providers.SchedulerProvider
import io.reactivex.rxjava3.core.Single

abstract class UseCase<in Params, T> protected constructor(private val schedulerProvider: SchedulerProvider) {

    protected abstract fun buildUseCaseObservable(params: Params?): Single<T>

    fun execute(params: Params? = null): Single<T> {
        return buildUseCaseObservable(params)
            .subscribeOn(schedulerProvider.subscribeOn)
            .observeOn(schedulerProvider.observeOn)
    }
}