package com.example.gymappandroid.di

import com.example.gymappandroid.data.firebase.FirebaseSource
import com.example.gymappandroid.data.firestore.products_data_source.FirestoreProductsDataSource
import com.example.gymappandroid.data.firestore.user_data_source.FireStoreUserDataSource
import com.example.gymappandroid.data.repositories.ProductsDataRepository
import com.example.gymappandroid.data.repositories.UserAuthRepository
import com.example.gymappandroid.data.repositories.UserDataRepository
import com.example.gymappandroid.ui.account.auth.details.UserDetailsViewModel
import com.example.gymappandroid.ui.account.auth.login.LoginViewModel
import com.example.gymappandroid.ui.account.auth.register.RegisterViewModel
import com.example.gymappandroid.ui.menu.main_menu.MainScreenViewModel
import com.example.gymappandroid.ui.menu.shop.ShopViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { FirebaseSource() }
    single { FireStoreUserDataSource() }
    single { FirestoreProductsDataSource() }
    single { UserAuthRepository(get()) }
    single { UserDataRepository(get()) }
    single { ProductsDataRepository(get()) }
}

val viewModelModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel { RegisterViewModel(get()) }
    viewModel { UserDetailsViewModel(get()) }
    viewModel { MainScreenViewModel(get()) }
    viewModel { ShopViewModel(get()) }
}
