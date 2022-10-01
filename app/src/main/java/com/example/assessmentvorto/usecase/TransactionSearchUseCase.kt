package com.example.assessmentvorto.usecase

import com.example.assessmentvorto.model.LatLngSearchRequest
import com.example.assessmentvorto.model.MessageResponse
import com.example.assessmentvorto.providers.SchedulerProvider
import com.example.assessmentvorto.repo.MainRepository
import io.reactivex.rxjava3.core.Single

class TransactionSearchUseCase(
    schedulerProvider: SchedulerProvider,
    private val mainRepository: MainRepository
) : UseCase<LatLngSearchRequest, MessageResponse>(schedulerProvider) {
    override fun buildUseCaseObservable(params: LatLngSearchRequest?): Single<MessageResponse> {
        return mainRepository.getTransactionSearch(params!!.latitude, params.longitude)
    }

}