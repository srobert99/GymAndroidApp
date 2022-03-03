package com.example.gymappandroid.ui.account.user_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gymappandroid.data.models.User
import com.example.gymappandroid.data.repositories.UserDataRepository

class UserDetailsViewModel(
    private val userDataRepository: UserDataRepository,
) : ViewModel() {

    private val _firestoreStatus = MutableLiveData("")
    val firestoreStatus: LiveData<String> = _firestoreStatus

    private val _isMale = MutableLiveData(false)
    val isMale: LiveData<Boolean> = _isMale

    private val _name = MutableLiveData("")
    val name: LiveData<String> = _name

    private val _surname = MutableLiveData("")
    val surname: LiveData<String> = _surname

    private val _phoneNumber = MutableLiveData("")
    val phoneNumber: LiveData<String> = _phoneNumber

    private val _birthdate = MutableLiveData("")
    val birthdate: LiveData<String> = _birthdate

    private var currentUser = User()

    suspend fun saveUserProfile() {
        if (verifyCredentials()) {
            _firestoreStatus.value = userDataRepository.createUserProfile(currentUser)
        } else {
            _firestoreStatus.value = "Check fields again"
        }
    }

    fun createUserProfile(uid: String?, email: String?) {
        currentUser = User(
            uid ?: "",
            name.value!!,
            surname.value!!,
            isMale.value!!,
            phoneNumber.value!!,
            birthdate.value!!,
            email = email!!
        )
    }

    fun onNameChange(newName: String) {
        _name.value = newName
    }

    fun onSurnameChange(newSurname: String) {
        _surname.value = newSurname
    }

    fun onPhoneNumberChange(newPhoneNumber: String) {
        _phoneNumber.value = newPhoneNumber
    }

    fun onGenderSelection(isMale: Boolean) {
        _isMale.value = isMale
    }

    fun onBirthDateSelect(birthdate: String) {
        _birthdate.value = birthdate
    }

    private fun verifyCredentials(): Boolean {
        return (name.value!!.isNotEmpty() && surname.value!!.isNotEmpty() && phoneNumber.value!!.isNotEmpty())
    }
}