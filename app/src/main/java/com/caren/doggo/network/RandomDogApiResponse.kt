package com.caren.doggo.network

import com.squareup.moshi.Json

data class RandomDogApiResponse(
    @Json(name = "message")
    val message: String?,

    @Json(name = "success")
    val success: String?
)