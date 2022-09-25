package com.example.assessmentvorto.ui

import android.content.Context
import android.util.Log
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
    context: Context,
    private val transactionSearchUseCase: TransactionSearchUseCase
) : ViewModel() {

    val businessList: MutableList<Business> = mutableListOf()
    var adapterModels = MutableLiveData<MutableList<Business>>()

    fun requestTransaction(latitude: String, longitude: String) {
        val latLngSearchRequest = LatLngSearchRequest(latitude, longitude)
        transactionSearchUseCase.execute(latLngSearchRequest)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onSuccess = {
                    Log.e("TAG", "CreatedAT: ${it}")
                    businessList.addAll(it.business)
                    adapterModels.postValue(businessList)
                },
                onError = {
                    Log.e("ERROR", "Exception: $it")
                }
            )

    }

}