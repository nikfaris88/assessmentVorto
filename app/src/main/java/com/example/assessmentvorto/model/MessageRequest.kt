package com.example.assessmentvorto.model

import com.google.gson.annotations.SerializedName

data class MessageRequest(
    @SerializedName("message")
    var message: String
)

