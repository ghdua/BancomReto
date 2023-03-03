package com.example.bancom.Data.Network

import com.example.bancom.Core.RetrofitHelper
import com.example.bancom.Data.Model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.create

class UserService {
    private val retrofit = RetrofitHelper.GetRetrofit()
    suspend fun getAllUsers(): List<User> {
        return withContext(Dispatchers.IO) {
            val response: Response<List<User>> = retrofit.create(UserApiClient::class.java).getAllUsers()
            response.body()!!
        }
    }

    suspend fun getAllPostsByUserID(userID: Int): List<Post> {
        return withContext(Dispatchers.IO){
            val response: Response<List<Post>> = retrofit.create(UserApiClient::class.java).getAllPostsByUserID(userID)
            response.body()!!
        }
    }

    suspend fun createePost(pPostRequest: PostRequest):PostResponse {
        return withContext(Dispatchers.IO){
            val response = retrofit.create(UserApiClient::class.java).createPost(pPostRequest)
            response
        }
    }
}