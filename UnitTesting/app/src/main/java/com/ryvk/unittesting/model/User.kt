package com.ryvk.unittesting.model

data class User(
    val id: String,
    val username: String,
    val email: String,
    val passwordHash: String,
    val isActive: Boolean = true,
    val roles: List<String> = listOf("USER")
)