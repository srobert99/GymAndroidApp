package com.example.gymappandroid.ui.account.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gymappandroid.data.models.User
import com.example.gymappandroid.data.repositories.UserRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.lang.Exception

class AuthViewModel(
    private val repository: UserRepository
) : ViewModel() {

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

    var authListener: AuthListener? = null

    private val disposables = CompositeDisposable()

    val user by lazy {
        repository.currentUser()
    }

    fun login() {
        if (email.value != null && password.value != null) {
            authListener?.onStarted()

            val disposable = repository.login(email = email.value!!, password.value!!)
                .subscribeOn(Schedulers.io())
                .subscribe({
                    authListener?.onSucces()
                }, {
                    authListener?.onFailure(it.message!!)
                    Log.d("Login", "Password/email wrong")
                })
            disposables.add(disposable)

            authListener?.onStarted()
        } else {
            //authListener?.onFailure("Something went wrong")
            Log.d("Login", "Something went wrong")
        }
    }

    fun signup() {
        if (email.value != null && password.value != null && password.value == confirmedPassword.value && phoneNumber.value != null && name.value != null) {
            authListener?.onStarted()
            val disposable = repository.register(email.value!!, password.value!!)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    authListener?.onSucces()
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
            user!!.uid,
            name.value!!,
            lastname.value!!,
            isMale.value!!,
            phoneNumber.value!!,
            birthDate.value!!,
            email = user?.email!!
        )
        try {
            repository.saveUserData(curUserDetails)
        } catch (e: Exception) {
            Log.d("Firestore Error", e.toString())
        }
    }

    fun getUserData() = repository.getUserData(user!!.uid)

    fun logout() {
        repository.logout()
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }

    fun onEmailChange(newEmail: String = "") {
        _email.value = newEmail
    }

    fun onNameChange(newName: String) {
        _firstname.value = newName
    }

    fun onLastNameChange(newLastName:String) {
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

    fun onBirthDateChange(newBirthDate:String){
        _birthDate.value = newBirthDate
    }

    fun onGenderSelection(isMale:Boolean){
        _isMale.value = isMale
    }
}
