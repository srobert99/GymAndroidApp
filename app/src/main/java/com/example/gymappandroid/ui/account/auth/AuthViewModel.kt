package com.example.gymappandroid.ui.account.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymappandroid.data.models.User
import com.example.gymappandroid.data.repositories.UserRepository
import com.google.firebase.auth.FirebaseUser
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class AuthViewModel(
    private val repository: UserRepository
) : ViewModel() {

    var firebaseUser: FirebaseUser? = null

    private val _userDetails = MutableLiveData<User>()
    val userDetails: LiveData<User> = _userDetails

    val moveToMain = MutableLiveData(false)

    private val _firstname = MutableLiveData("")
    val name: LiveData<String> = _firstname

    private val _lastname = MutableLiveData("")
    val lastname: LiveData<String> = _lastname

    private val _phoneNumber = MutableLiveData("")
    val phoneNumber: LiveData<String> = _phoneNumber

    private val _email = MutableLiveData("")
    val email: LiveData<String> = _email

    private val _password = MutableLiveData("")
    val password: LiveData<String> = _password

    private val _isMale = MutableLiveData(true)
    val isMale: LiveData<Boolean> = _isMale

    private val _birthDate = MutableLiveData("")
    val birthDate: LiveData<String> = _birthDate

    private val _confirmedPassword = MutableLiveData("")
    val confirmedPassword: LiveData<String> = _confirmedPassword

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val disposables = CompositeDisposable()

    init {
        getFirebaseUser()
    }

    suspend fun login() {
        if (email.value != null && password.value != null) {
                load()
                repository.login(email.value!!, password.value!!)
                moveToMain.postValue(true)
                load()
        }
    }

    fun signup() {
        if (email.value != null && password.value != null && password.value == confirmedPassword.value) {
            viewModelScope.launch(Dispatchers.IO) {
                repository.register(email.value!!, password.value!!)
            }
        }
    }

    fun saveUserData() {
        val curUserDetails = User(
            firebaseUser!!.uid,
            name.value!!,
            lastname.value!!,
            isMale.value!!,
            phoneNumber.value!!,
            birthDate.value!!,
            email = firebaseUser!!.email!!
        )
        viewModelScope.launch {
            load()
            repository.saveUserData(curUserDetails)
            load()
        }
    }

    fun getUserData() {
        viewModelScope.launch(Dispatchers.IO) {
            _userDetails.postValue(repository.getUserData(firebaseUser!!.uid))
        }
    }

    fun logout() {
        repository.logout()
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }

    fun load() {
        _isLoading.postValue(!_isLoading.value!!)
    }

    fun onEmailChange(newEmail: String = "") {
        _email.value = newEmail
    }

    fun onNameChange(newName: String) {
        _firstname.value = newName
    }

    fun onLastNameChange(newLastName: String) {
        _lastname.value = newLastName
    }

    fun onPasswordChange(newPassword: String) {
        _password.value = newPassword
    }

    fun onCPasswordChange(newCPassword: String) {
        _confirmedPassword.value = newCPassword
    }

    fun onPhoneNumberChange(newPhoneNumber: String) {
        _phoneNumber.value = newPhoneNumber
    }

    fun onBirthDateChange(newBirthDate: String) {
        _birthDate.value = newBirthDate
    }

    fun onGenderSelection(isMale: Boolean) {
        _isMale.value = isMale
    }

    private fun getFirebaseUser() {
        firebaseUser = repository.currentUser()
    }
}
