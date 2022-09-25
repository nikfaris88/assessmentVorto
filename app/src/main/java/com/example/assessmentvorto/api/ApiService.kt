package com.example.assessmentvorto.api

import com.example.assessmentvorto.model.MessageResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @POST("transactions/delivery/search?")
    fun transactionSearch(
        @Query("latitude") latitude: String,
        @Query("longitude") longitude: String
    ): Single<MessageResponse>
}