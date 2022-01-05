package com.example.gymappandroid.data.models

data class User(
    val name: String,
    val surname: String,
    val isMale: Boolean,
    val phoneNumber: String,
    val birthDate: String,
    val balance:Int = 0,
    val email: String
)