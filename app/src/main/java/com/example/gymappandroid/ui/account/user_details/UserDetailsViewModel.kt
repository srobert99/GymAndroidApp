package com.example.gymappandroid.ui.account.user_details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymappandroid.data.models.User
import com.example.gymappandroid.data.repositories.UserDataRepository
import kotlinx.coroutines.launch

class UserDetailsViewModel(
    private val userDataRepository: UserDataRepository,
    uid: String
) : ViewModel() {
    val currentUser = MutableLiveData(User())

    init {
        viewModelScope.launch {

        }
    }
}