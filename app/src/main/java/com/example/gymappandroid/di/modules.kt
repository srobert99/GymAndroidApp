package com.example.gymappandroid.di

import com.example.gymappandroid.data.firebase.FirebaseSource
import com.example.gymappandroid.data.repositories.UserAuthRepository
import com.example.gymappandroid.ui.account.auth.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { FirebaseSource() }
    single { UserAuthRepository(get()) }
}

val authViewModelModule = module{
    viewModel { LoginViewModel(get()) }
}
