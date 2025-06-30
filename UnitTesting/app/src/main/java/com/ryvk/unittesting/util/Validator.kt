package com.ryvk.unittesting.util

class Validator {
    fun isValidEmail(email: String): Boolean {
        return email.matches(Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$"))
    }

    fun isValidUsername(username: String): Boolean {
        return username.length in 3..20 && username.matches(Regex("^[a-zA-Z0-9_]*\$"))
    }

    fun isStrongPassword(password: String): Boolean {
        return password.length >= 8 &&
                password.any { it.isDigit() } &&
                password.any { it.isLetter() }
    }
}