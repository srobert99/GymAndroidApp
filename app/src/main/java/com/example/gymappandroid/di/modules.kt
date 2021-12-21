package com.example.gymappandroid.di

import com.example.gymappandroid.data.firebase.FirebaseSource
import com.example.gymappandroid.data.repositories.UserRepository
import com.example.gymappandroid.ui.account.auth.AuthViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { FirebaseSource() }
    single { UserRepository(get()) }
}

val authViewModelModule = module{
    viewModel { AuthViewModel(get())}
}
