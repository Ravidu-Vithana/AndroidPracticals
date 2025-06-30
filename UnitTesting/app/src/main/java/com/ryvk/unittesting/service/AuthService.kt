package com.ryvk.unittesting.service

import com.ryvk.unittesting.model.User
import com.ryvk.unittesting.repository.UserRepository
import com.ryvk.unittesting.util.Validator

class AuthService(
    private val userRepository: UserRepository,
    private val validator: Validator
) {
    fun register(user: User): User {
        require(validator.isValidEmail(user.email)) { "Invalid email" }
        require(validator.isValidUsername(user.username)) { "Invalid username" }

        return userRepository.save(user)
    }

    fun login(username: String, passwordHash: String): User? {
        val user = userRepository.findByUsername(username)
        return if (user?.passwordHash == passwordHash) user else null
    }
}