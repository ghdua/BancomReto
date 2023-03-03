package com.example.bancom.Data.Model

import com.google.gson.annotations.SerializedName

data class Address(
    @SerializedName("street") var Street: String,
    @SerializedName("suite") var Suite: String,
    @SerializedName("city") var City: String,
    @SerializedName("zipcode") var ZipCode: String,
    @SerializedName("geo") var Geo: Geolocation
)
