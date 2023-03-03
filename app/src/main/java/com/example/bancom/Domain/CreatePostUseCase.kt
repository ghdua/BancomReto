package com.example.bancom.Domain

import com.example.bancom.Data.Model.Post
import com.example.bancom.Data.Model.PostRequest
import com.example.bancom.Data.Model.PostResponse
import com.example.bancom.Data.Repository.UsersRepository

class CreatePostUseCase {
    private val repository = UsersRepository()
    suspend operator fun invoke(pPostRequest: PostRequest): PostResponse = repository.createPost(pPostRequest)
}