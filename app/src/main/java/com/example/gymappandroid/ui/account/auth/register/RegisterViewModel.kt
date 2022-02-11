package com.example.gymappandroid.ui.account.auth.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gymappandroid.data.repositories.UserAuthRepository

class RegisterViewModel(
    private val userAuthRepository: UserAuthRepository
) : ViewModel() {

    private val _email = MutableLiveData("")
    val email: LiveData<String> = _email

    private val _password = MutableLiveData("")
    val password: LiveData<String> = _password

    private val _confirmPassword = MutableLiveData("")
    val confirmPassword = _confirmPassword

    private val _firebaseStatus = MutableLiveData("")
    val firebaseStatus: LiveData<String> = _firebaseStatus

    suspend fun register() {
        if (verifyCredentials()) {
            val email = email.value.toString()
            val password = password.value.toString()
            _firebaseStatus.value = userAuthRepository.register(email, password)
        }
    }

    fun onEmailChange(newEmail: String) {
        _email.value = newEmail
    }

    fun onPasswordChange(newPassword: String) {
        _password.value = newPassword
    }

    fun onConfirmPasswordChange(newConfirmPassword: String) {
        _confirmPassword.value = newConfirmPassword
    }

    private fun verifyCredentials(): Boolean {
        if (email.value == null || password.value == null || password.value != confirmPassword.value) {
            _firebaseStatus.value = "passwords doesn't match"
            return false
        }
        return true
    }

    fun getFirebaseUID(): String? {
        return userAuthRepository.currentUser()?.uid
    }
}