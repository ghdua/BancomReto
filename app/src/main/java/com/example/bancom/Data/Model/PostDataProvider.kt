package com.example.bancom.Data.Model

class PostDataProvider {
    companion object{
        lateinit var getPostsData: List<Post>
        var PostListResult = mutableListOf<List<Post>>()
    }
}