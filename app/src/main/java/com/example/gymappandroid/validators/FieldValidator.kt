package com.example.gymappandroid.validators

interface FieldValidator<out T> {

    fun validate(input: String): T
}