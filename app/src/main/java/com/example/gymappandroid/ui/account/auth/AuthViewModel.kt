package com.example.gymappandroid.ui.account.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymappandroid.data.models.User
import com.example.gymappandroid.data.repositories.UserRepository
import com.google.firebase.auth.FirebaseUser
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AuthViewModel(
    private val repository: UserRepository
) : ViewModel() {

    var firebaseUser: FirebaseUser? = null

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

    private var authListener: AuthListener? = null

    private val disposables = CompositeDisposable()

    init {
        getFirebaseUser()
    }

    fun login() {
        if (email.value != null && password.value != null) {
            viewModelScope.launch(Dispatchers.IO) {
                load()
                repository.login(email.value!!, password.value!!)
                load()
            }
        }
    }

    fun signup() {
        if (email.value != null && password.value != null && password.value == confirmedPassword.value) {
            authListener?.onStarted()
            val disposable = repository.register(email.value!!, password.value!!)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    authListener?.onSucces()
                    getFirebaseUser()
                }, {
                    authListener?.onFailure(it.message!!)
                })
            disposables.add(disposable)
        } else {
            authListener?.onFailure("Something went wrong")
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

    fun getUserData() = repository.getUserData(firebaseUser!!.uid)

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

    private fun getFirebaseUser(){
        firebaseUser = repository.currentUser()
    }
}
