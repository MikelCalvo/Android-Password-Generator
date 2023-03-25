package com.mikelcalvo.passwordgenerator.data

class PasswordGenerator {
    fun generate(length: Int, useUppercase: Boolean, useNumbers: Boolean, useSpecialChars: Boolean): String {
        val lowercase = "abcdefghijklmnopqrstuvwxyz"
        val uppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        val numbers = "0123456789"
        val specialChars = "!@#\$%^&*()-_=+/<>?"

        var passwordChars = lowercase
        if (useUppercase) passwordChars += uppercase
        if (useNumbers) passwordChars += numbers
        if (useSpecialChars) passwordChars += specialChars

        return (1..length)
            .map { passwordChars.random() }
            .joinToString("")
    }
}
