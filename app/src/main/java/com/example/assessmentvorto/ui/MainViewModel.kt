package com.example.assessmentvorto.ui

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.assessmentvorto.model.Business
import com.example.assessmentvorto.model.LatLngSearchRequest
import com.example.assessmentvorto.usecase.TransactionSearchUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val transactionSearchUseCase: TransactionSearchUseCase
) : ViewModel() {

    val data: LiveData<List<Business>>
        get() = _data
    private val _data = MutableLiveData<List<Business>>(emptyList())

    fun requestTransaction(latitude: String, longitude: String) {
        val latLngSearchRequest = LatLngSearchRequest(latitude, longitude)
        transactionSearchUseCase.execute(latLngSearchRequest)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onSuccess = {
                    Log.d("TAG", "CreatedAT: ${it}")
                    _data.postValue(it.businesses)
                },
                onError = {
                    Log.d("ERROR", "Exception: ${it}")
                }
            )

    }

}