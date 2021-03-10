package com.example.githubuserapp.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.githubuserapp.model.User
import java.lang.IllegalArgumentException

class MainViewModelFactory(private val userList: List<User>): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(userList) as T
        }
        throw IllegalArgumentException("Viewmodel yang diminta MainViewModel")
    }
}