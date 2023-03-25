package com.mikelcalvo.passwordgenerator.viewmodel

import androidx.lifecycle.ViewModel
import com.mikelcalvo.passwordgenerator.data.PasswordGenerator

class MainViewModel : ViewModel() {
    private val passwordGenerator = PasswordGenerator()

    fun generatePassword(length: Int, useUppercase: Boolean, useNumbers: Boolean, useSpecialChars: Boolean): String {
        return passwordGenerator.generate(length, useUppercase, useNumbers, useSpecialChars)
    }
}