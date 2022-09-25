package com.example.assessmentvorto.api

import com.example.assessmentvorto.providers.SchedulerProvider
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.schedulers.Schedulers.io

class AppSchedulers: SchedulerProvider {
    override val subscribeOn: Scheduler
        get() = io()
    override val observeOn: Scheduler
        get() = AndroidSchedulers.mainThread()
    override val newThread: Scheduler
        get() = Schedulers.newThread()
}