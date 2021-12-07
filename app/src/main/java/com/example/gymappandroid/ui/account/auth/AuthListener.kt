package com.example.gymappandroid.ui.account.auth

interface AuthListener {
    fun onStarted()
    fun onSucces()
    fun onFailure(message:String)
}