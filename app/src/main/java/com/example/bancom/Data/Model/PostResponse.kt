package com.example.bancom.Data.Model

import com.google.gson.annotations.SerializedName

data class PostResponse(
    @SerializedName("title") var PostTitle: String,
    @SerializedName("body") var PostBody: String,
    @SerializedName("userId") var UserId: Int,
    @SerializedName("id") var PostID: Int
)
