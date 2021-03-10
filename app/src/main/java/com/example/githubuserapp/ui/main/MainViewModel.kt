package com.example.githubuserapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubuserapp.model.User

class MainViewModel : ViewModel{
    private var listUserMutableLiveData = MutableLiveData<List<User>>()
    private var userList: List<User>? = null

    constructor(userList: List<User>) {
        this.userList = userList
        listUserMutableLiveData.value = userList
    }

    fun listUserLiveData(): LiveData<List<User>> {
        return listUserMutableLiveData
    }

    private val _navigatetoDetail = MutableLiveData<User?>()
    fun navigatetoDetail(): LiveData<User?> {
        return _navigatetoDetail
    }

    fun onUserClicked(user: User?) {
        _navigatetoDetail.value = user
    }

    fun onUserMainDetailNavigated() {
        _navigatetoDetail.value = null
    }
}