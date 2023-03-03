package com.example.bancom.UI.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bancom.Data.Model.Post
import com.example.bancom.Data.Model.PostRequest
import com.example.bancom.Data.Model.PostResponse
import com.example.bancom.Data.Model.PostsResponse
import com.example.bancom.Domain.CreatePostUseCase
import com.example.bancom.Domain.GetPostsUseCase
import kotlinx.coroutines.launch

class PostsViewModel: ViewModel() {
    val postsResponse = MutableLiveData<List<Post>?>()
    val addPostResponse = MutableLiveData<PostResponse>()
    val isLoading = MutableLiveData<Boolean>()
    lateinit var getPostsUseCase: GetPostsUseCase
    lateinit var createPostUseCase: CreatePostUseCase

    fun onCreate(userID: Int){
        viewModelScope.launch{
            isLoading.postValue(true)
            getPostsUseCase = GetPostsUseCase()
            val result: List<Post> = getPostsUseCase.invoke(userID)
            if(result!=null){
                postsResponse.postValue(result)
                isLoading.postValue(false)
            }
        }
    }

    fun addPost(pPostRequest: PostRequest){
        viewModelScope.launch {
            isLoading.postValue(true)
            createPostUseCase = CreatePostUseCase()
            val result = createPostUseCase.invoke(pPostRequest)
            if(result!=null){
                addPostResponse.postValue(result)
                isLoading.postValue(false)
            }
        }
    }
}