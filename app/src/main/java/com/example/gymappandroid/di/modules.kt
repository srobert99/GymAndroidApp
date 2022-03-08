package com.example.gymappandroid.di

import com.example.gymappandroid.data.firebase.FirebaseSource
import com.example.gymappandroid.data.firestore.FireStoreSource
import com.example.gymappandroid.data.repositories.UserAuthRepository
import com.example.gymappandroid.data.repositories.UserDataRepository
import com.example.gymappandroid.ui.account.auth.login.LoginViewModel
import com.example.gymappandroid.ui.account.auth.register.RegisterViewModel
import com.example.gymappandroid.ui.account.user_details.UserDetailsViewModel
import com.example.gymappandroid.ui.menu.main_menu.MainScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { FirebaseSource() }
    single { FireStoreSource() }
    single { UserAuthRepository(get()) }
    single { UserDataRepository(get()) }
}

val authViewModelModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel { RegisterViewModel(get()) }
    viewModel { UserDetailsViewModel(get()) }
    viewModel { MainScreenViewModel(get()) }
}
