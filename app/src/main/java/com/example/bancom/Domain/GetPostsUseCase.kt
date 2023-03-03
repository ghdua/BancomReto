package com.example.bancom.Domain

import com.example.bancom.Data.Model.Post
import com.example.bancom.Data.Model.PostsResponse
import com.example.bancom.Data.Repository.UsersRepository

class GetPostsUseCase {
    private val repository = UsersRepository()
    suspend operator fun invoke(userID: Int): List<Post> = repository.getAllPostsByUserID(userID)
}