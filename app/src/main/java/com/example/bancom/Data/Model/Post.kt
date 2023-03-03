package com.example.bancom.Data.Model

import com.google.gson.annotations.SerializedName

data class Post(
    @SerializedName("userId") var UserID: Int,
    @SerializedName("id") var ID: Int,
    @SerializedName("title") var Title: String,
    @SerializedName("body") var Body: String
)
