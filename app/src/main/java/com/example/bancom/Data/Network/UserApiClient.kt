package com.example.bancom.Data.Network

import com.example.bancom.Data.Model.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface UserApiClient {
    @GET("users")
    suspend fun getAllUsers(): Response<List<User>>
    @GET("users/{userId}/posts")
    suspend fun getAllPostsByUserID(@Path("userId") userID: Int): Response<List<Post>>
    @POST("posts")
    suspend fun createPost(@Body postRequest:PostRequest): PostResponse
}