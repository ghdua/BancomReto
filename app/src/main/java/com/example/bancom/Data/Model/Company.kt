package com.example.bancom.Data.Model

import com.google.gson.annotations.SerializedName

data class Company(
    @SerializedName("name") var Name: String,
    @SerializedName("catchPhrase") var CatchPhrase: String,
    @SerializedName("bs") var BS: String
)
