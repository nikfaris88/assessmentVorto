package com.example.assessmentvorto.repo

import com.example.assessmentvorto.api.ApiService
import com.example.assessmentvorto.model.MessageResponse
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiService: ApiService) {

    fun getTransactionSearch(latitude: String, longitude: String): Single<MessageResponse> =
        apiService.transactionSearch(latitude, longitude)
}