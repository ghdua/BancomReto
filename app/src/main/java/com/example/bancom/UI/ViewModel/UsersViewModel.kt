package com.example.bancom.UI.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bancom.Data.Model.User
import com.example.bancom.Data.Model.UsersResponse
import com.example.bancom.Domain.GetUsersUseCase
import kotlinx.coroutines.launch

class UsersViewModel: ViewModel() {
    val usersResponse = MutableLiveData<List<User>?>()
    val isLoading = MutableLiveData<Boolean>()
    lateinit var getUsersUseCase: GetUsersUseCase

    fun onCreate(){
        viewModelScope.launch {
            isLoading.postValue(true)
            getUsersUseCase = GetUsersUseCase()
            val result: List<User> = getUsersUseCase.invoke()
            if(result!=null){
                usersResponse.postValue(result)
                isLoading.postValue(false)
            }
        }
    }
}