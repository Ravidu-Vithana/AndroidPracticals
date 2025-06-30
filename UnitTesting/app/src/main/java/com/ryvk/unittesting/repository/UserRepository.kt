package com.ryvk.unittesting.repository

import com.ryvk.unittesting.model.User

interface UserRepository {
    fun save(user: User): User
    fun findById(id: String): User?
    fun findByUsername(username: String): User?
    fun findAll(): List<User>
    fun delete(id: String): Boolean
}