package com.example.gymappandroid.data.models

data class User(
    var uid: String = "",
    var name: String = "",
    var surname: String = "",
    var isMale: Boolean = true,
    var phoneNumber: String = "",
    var birthDate: String = " ",
    var balance: Int = 0,
    var email: String = ""
)