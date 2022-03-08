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

    private var canLogin: Boolean = false

    init {
        firebaseUser = authRepository.currentUser()
    }

    suspend fun login() {
        validateCredentials()
        if (canLogin) {
            _firebaseStatus.value =
                (authRepository.login(this.email.value.toString(), this.password.value.toString()))
        } else {
            _firebaseStatus.value = "Username/password can't be empty"
        }
    }

    fun logout() {
        authRepository.logout()
    }

    fun onEmailChange(newEmail: String) {
        _email.value = newEmail
    }

    fun onPasswordChange(newPassword: String) {
        _password.value = newPassword
    }

    fun getUID(): String? {
        return authRepository.currentUser()?.uid
    }

    private fun validateCredentials() {
        if (email.value!!.isNotEmpty() && password.value!!.isNotEmpty()) {
            canLogin = true
        }
    }
}
