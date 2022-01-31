package com.example.gymappandroid.ui.account.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gymappandroid.data.repositories.UserAuthRepository
import com.example.gymappandroid.ui.account.auth.login.LoginViewModel

@Suppress("UNCHECKED_CAST")
class AuthViewModelFactory(private val authRepository: UserAuthRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModel(authRepository) as T
    }
}