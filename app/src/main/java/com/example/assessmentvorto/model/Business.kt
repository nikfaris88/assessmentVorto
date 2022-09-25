package com.example.assessmentvorto.model

import com.google.gson.annotations.SerializedName

data class Business(
    @SerializedName("id")
    val id: String,
    @SerializedName("alias")
    val alias: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("image_url")
    val image_url: String,
    @SerializedName("is_closed")
    val is_closed: Boolean,
    @SerializedName("url")
    val url: String,
    @SerializedName("review_count")
    val review_count: Int,
    @SerializedName("categories")
    val category: List<Category>,
    @SerializedName("rating")
    val rating: Double,
    @SerializedName("coordinates")
    val coordinates: Coordinates,
    @SerializedName("transaction")
    val transaction: List<Transaction>,
    @SerializedName("price")
    val price: String,
    @SerializedName("location")
    val location: Location,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("display_phone")
    val display_phone: String,


    )
