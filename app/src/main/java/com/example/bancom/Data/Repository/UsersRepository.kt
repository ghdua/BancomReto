package com.example.bancom.Data.Repository

import com.example.bancom.Data.Model.*
import com.example.bancom.Data.Network.UserService

class UsersRepository {
    private val api = UserService()
    suspend fun getAllUsers(): List<User>
    {
        val response = api.getAllUsers()
        UserDataProvider.getUsersData = response
        return response
    }

    suspend fun getAllPostsByUserID(userID: Int): List<Post>{
        val response = api.getAllPostsByUserID(userID)
        PostDataProvider.getPostsData = response
        return response
    }

    suspend fun createPost(pPostRequest: PostRequest): PostResponse{
        val response = api.createePost(pPostRequest)
        return response
    }
}