package com.example.bancom.Data.Model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id") var UserID: Int,
    @SerializedName("name") var Name: String,
    @SerializedName("username") var UserName: String,
    @SerializedName("email") var EMail: String,
    @SerializedName("address") var address: Address,
    @SerializedName("phone") var Phone: String,
    @SerializedName("website") var WebSite: String,
    @SerializedName("company") var company: Company
)
