package com.example.bancom.Data.Model

import com.google.gson.annotations.SerializedName

data class PostRequest (
    @SerializedName("title") var PostTitle: String,
    @SerializedName("body") var PostBody: String,
    @SerializedName("userId") var UserId: Int
)