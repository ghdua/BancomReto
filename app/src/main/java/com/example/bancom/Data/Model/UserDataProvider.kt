package com.example.bancom.Data.Model

class UserDataProvider {
    companion object{
        lateinit var getUsersData: List<User>
        var UserListResult = mutableListOf<List<User>>()
    }
}