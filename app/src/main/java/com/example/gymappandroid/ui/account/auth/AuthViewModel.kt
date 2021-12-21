package com.example.gymappandroid.ui.account.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gymappandroid.data.repositories.UserRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class AuthViewModel(
    private val repository: UserRepository
) : ViewModel() {

    private val _name = MutableLiveData("")
    val name:LiveData<String> = _name

    private val _phoneNumber = MutableLiveData("")
    val phoneNumber:LiveData<String> = _phoneNumber

    private val _email = MutableLiveData("")
    val email: LiveData<String> = _email

    private val _password = MutableLiveData("")
    val password: LiveData<String> = _password

    private val _confirmedPassword = MutableLiveData("")
    val confirmedPassword: LiveData<String> = _confirmedPassword

    var authListener: AuthListener? = null

    private val disposables = CompositeDisposable()

    val user by lazy {
        repository.currentUser()
    }

    fun login() {
        if (email.value != null && password.value != null && password.value == confirmedPassword.value) {
            authListener?.onStarted()

            val disposable = repository.login(email = email.value!!, password.value!!)
                .subscribeOn(Schedulers.io())
                .subscribe({
                    authListener?.onSucces()
                }, {
                    authListener?.onFailure(it.message!!)
                })
            disposables.add(disposable)

            authListener?.onStarted()
        }else{
            authListener?.onFailure("Something went wrong")
        }
    }

    fun signup() {
        if (email.value != null && password.value != null && password.value == confirmedPassword.value){
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
        }else{
            authListener?.onFailure("Something went wrong")
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }

    fun onEmailChange(newEmail: String =""){
        _email.value = newEmail
    }

    fun onNameChange(newName:String){
        _name.value = newName
    }

    fun onPasswordChange(newPassword:String){
        _password.value = newPassword
    }

    fun onCPasswordChange(newCPassword: String){
        _confirmedPassword.value = newCPassword
    }

    fun onPhoneNumberChange(newPhoneNumber: String) {
        _phoneNumber.value = newPhoneNumber
    }
}
