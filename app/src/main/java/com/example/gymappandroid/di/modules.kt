package com.example.gymappandroid.di

import com.example.gymappandroid.data.firebase.FirebaseSource
import com.example.gymappandroid.data.firestore.FireStoreSource
import com.example.gymappandroid.data.repositories.UserAuthRepository
import com.example.gymappandroid.ui.account.auth.login.LoginViewModel
import com.example.gymappandroid.ui.account.auth.register.RegisterViewModel
import com.example.gymappandroid.ui.account.user_details.UserDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { FirebaseSource() }
    single { UserAuthRepository(get()) }
    single { FireStoreSource() }
    single { RegisterViewModel(get()) }
    single { UserDetailsViewModel(get()) }
}

val authViewModelModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel { RegisterViewModel(get()) }
    viewModel { UserDetailsViewModel(get()) }
}
