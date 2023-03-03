package com.example.bancom.Data.Model

import com.google.gson.annotations.SerializedName

data class Geolocation(
    @SerializedName("lat") var Latitude: String,
    @SerializedName("lng") var Longitude: String
)
