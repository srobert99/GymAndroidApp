package com.example.gymappandroid.ui.menu.main_menu

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gymappandroid.data.models.User
import com.example.gymappandroid.data.repositories.UserDataRepository

class MainScreenViewModel(
    val userDataRepository: UserDataRepository
) : ViewModel() {

    private val _firestoreStatus = MutableLiveData("")
    val firestoreStatus = _firestoreStatus

    private val _currentUser = MutableLiveData(User())
    val currentUser = _currentUser

    suspend fun getUserData(session: String?) {
        try {
            currentUser.postValue(userDataRepository.getUserProfile(session!!)!!)
        } catch (e: Exception) {
            firestoreStatus.postValue(e.message)
        }
    }
}