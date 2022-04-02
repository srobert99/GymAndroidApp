package com.example.gymappandroid.ui.account.auth.details

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

    private val _email = MutableLiveData("")
    val email: LiveData<String> = _email

    private val _coins = MutableLiveData("")
    val coins: LiveData<String> = _coins

    private val _phoneNumber = MutableLiveData("")
    val phoneNumber: LiveData<String> = _phoneNumber

    private val _birthdate = MutableLiveData("")
    val birthdate: LiveData<String> = _birthdate


    suspend fun saveUserProfile(uid: String?, email: String? = this.email.value) {
        val currentUser = User(
            uid ?: "",
            name.value!!,
            surname.value!!,
            isMale.value!!,
            phoneNumber.value!!,
            birthdate.value!!,
            email = email!!
        )
        if (verifyCredentials()) {
            _firestoreStatus.value = userDataRepository.createUserProfile(currentUser)
        } else {
            _firestoreStatus.value = "Check fields again"
        }
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

    suspend fun getUserProfile(userID: String) {
        val currentUser = (userDataRepository.getUserProfile(userID))

        currentUser?.let {
            _name.value = it.name
            _surname.value = it.surname
            _phoneNumber.value = it.phoneNumber
            _isMale.value = it.isMale
            _email.value = it.email
            _birthdate.value = it.birthDate
            _coins.value = it.balance.toString()
        }
    }

    private fun verifyCredentials(): Boolean {
        return (name.value!!.isNotEmpty() && surname.value!!.isNotEmpty() && phoneNumber.value!!.isNotEmpty())
    }

    fun clearFireStoreResponse() {
        _firestoreStatus.value = ""
    }
}
