package com.example.gymappandroid.validators

import com.example.gymappandroid.validators.EmailValidationStatus.Valid
import com.example.gymappandroid.validators.EmailValidationStatus.Invalid
import android.util.Patterns.EMAIL_ADDRESS

class EmailValidator : FieldValidator<EmailValidationStatus> {

    override fun validate(input: String): EmailValidationStatus {
        val emailParts = input.split("@")
        if (emailParts.size != 2) {
            return Invalid
        } else {
            val noEmptyParts = emailParts[0].isEmpty() || emailParts[1].isEmpty()
            val notMatchPattern = EMAIL_ADDRESS.matcher(input).matches()
            if (noEmptyParts || notMatchPattern) {
                return Invalid
            }
        }
        return Valid
    }

}

sealed class EmailValidationStatus {
    object Valid : EmailValidationStatus()
    object Invalid : EmailValidationStatus()
}

val EmailValidationStatus.isValid
    get() = this == Valid
