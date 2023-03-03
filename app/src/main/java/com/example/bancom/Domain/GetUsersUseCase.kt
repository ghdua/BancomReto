package com.example.bancom.Domain

import com.example.bancom.Data.Model.User
import com.example.bancom.Data.Model.UsersResponse
import com.example.bancom.Data.Repository.UsersRepository

class GetUsersUseCase {
    private val repository = UsersRepository()
    suspend operator fun invoke(): List<User> = repository.getAllUsers()
}