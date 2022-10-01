package com.example.assessmentvorto.model

import com.google.gson.annotations.SerializedName

data class MessageResponse(
    @SerializedName("businesses")
    val businesses: List<Business>,
    @SerializedName("total")
    val total: Int,
//    @SerializedName("region")
//    val region:Region
)