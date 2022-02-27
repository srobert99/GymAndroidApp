package com.example.gymappandroid.ui.account.auth.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gymappandroid.data.repositories.UserAuthRepository

class RegisterViewModel(
    private val userAuthRepository: UserAuthRepository
) : ViewModel() {

    private val _email = MutableLiveData("")
    val email: LiveData<String> = _email

    private val _password = MutableLiveData("")
    val password: LiveData<String> = _password

    private val _confirmPassword = MutableLiveData("")
    val confirmPassword = _confirmPassword

    private val _firebaseStatus = MutableLiveData("")
    val firebaseStatus: LiveData<String> = _firebaseStatus


    private val _emailErrorMessage = MutableLiveData("")
    val emailError: LiveData<String> = _emailErrorMessage

    private val _passwordErrorMessage = MutableLiveData("")
    val passwordError: LiveData<String> = _passwordErrorMessage

    private val _cPasswordErrorMessage = MutableLiveData("")
    val cPasswordError: LiveData<String> = _cPasswordErrorMessage
    private var hasErrors = mutableListOf(true, true, true)

    suspend fun register() {
        verifyCredentials()
        if (verifyCredentials()) {
            val email = email.value.toString()
            val password = password.value.toString()
            _firebaseStatus.value = userAuthRepository.register(email, password)
        } else {
            _firebaseStatus.value = "Check all fields again"
        }
    }

    fun onEmailChange(newEmail: String) {
        verifyEmail(newEmail)
        _email.value = newEmail
    }

    fun onPasswordChange(newPassword: String) {
        verifyPassword(newPassword)
        _password.value = newPassword
    }

    fun onConfirmPasswordChange(newConfirmPassword: String) {
        verifyConfirmPassword(newConfirmPassword)
        _confirmPassword.value = newConfirmPassword
    }

    fun getFirebaseUID(): String? {
        return userAuthRepository.currentUser()?.uid
    }

    private fun verifyEmail(input: String) {
        _emailErrorMessage.value = ""
        hasErrors[0] = false
        val emailParts = input.split("@")
        if (emailParts.size != 2) {
            _emailErrorMessage.value = "Email to short"
            hasErrors[0] = true
        } else {
            val arePartsEmpty = emailParts[0].isEmpty() || emailParts[1].isEmpty()
            val hasDomainPoint = emailParts[1].contains(".")
            val hasDomainPartsEmpty = emailParts[1].split(".").any { it.isEmpty() }
            if (arePartsEmpty || !hasDomainPoint || hasDomainPartsEmpty) {
                _emailErrorMessage.value = "Invalid Email"
                hasErrors[0] = true
            }
        }
    }

    private fun verifyPassword(input: String) {
        hasErrors[1] = false
        _passwordErrorMessage.value = ""
        if (input.length < 8) {
            hasErrors[1] = true
            _passwordErrorMessage.value = "Password to short"
        } else {
            var hasLowercase = false
            var hasUppercase = false
            var hasDigit = false
            var hasSpecialChar = false
            input.forEach {
                if (it.isLowerCase()) hasLowercase = true
                if (it.isUpperCase()) hasUppercase = true
                if (it.isDigit()) hasDigit = true
                if ("`~!@#$%^&*()_+-={}|[]\\;:'\"<>?,./".contains(it)) hasSpecialChar = true
            }
            if (!hasLowercase) {
                hasErrors[1] = true
                _passwordErrorMessage.value =
                    "Password needs to contain at least one lower case letter"
            }
            if (!hasUppercase) {
                hasErrors[1] = true
                _passwordErrorMessage.value =
                    "Password needs to contain at least one upper case letter"
            }
            if (!hasDigit) {
                hasErrors[1] = true
                _passwordErrorMessage.value = "Password needs to contain at least one digit"
            }
            if (!hasSpecialChar) {
                hasErrors[1] = true
                _passwordErrorMessage.value =
                    "Password needs to contain at least one special character"
            }
        }
    }

    private fun verifyConfirmPassword(input: String) {
        if (password.value != input) {
            _cPasswordErrorMessage.value = "Passwords doesn't match"
            hasErrors[2] = true
        } else {
            _cPasswordErrorMessage.value = ""
            hasErrors[2] = false
        }
    }

    private fun verifyCredentials(): Boolean = !hasErrors.contains(true)
}