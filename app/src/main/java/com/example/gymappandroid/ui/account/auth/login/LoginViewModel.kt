package com.example.gymappandroid.ui.account.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gymappandroid.data.repositories.UserAuthRepository
import com.google.firebase.auth.FirebaseUser

class LoginViewModel(
    private val authRepository: UserAuthRepository
) : ViewModel() {

    var firebaseUser: FirebaseUser? = null

    private val _firebaseStatus = MutableLiveData("")
    val firebaseStatus: LiveData<String> = _firebaseStatus

    private val _email = MutableLiveData("")
    val email: LiveData<String> = _email

    private val _password = MutableLiveData("")
    val password: LiveData<String> = _password

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        getFirebaseUser()
    }

    suspend fun login() {
        load()
        _firebaseStatus.value =
            (authRepository.login(this.email.value.toString(), this.password.value.toString()))
        load()
    }

    fun logout() {
        authRepository.logout()
    }

    private fun load() {
        _isLoading.postValue(!_isLoading.value!!)
    }

    fun onEmailChange(newEmail: String) {
        _email.value = newEmail
    }

    fun onPasswordChange(newPassword: String) {
        _password.value = newPassword
    }

    private fun getFirebaseUser() {
        firebaseUser = authRepository.currentUser()
    }
}
