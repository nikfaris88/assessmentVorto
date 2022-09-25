package com.example.assessmentvorto.model

import com.google.gson.annotations.SerializedName

data class Region(
    @SerializedName("center")
    val center: Coordinates,
)
