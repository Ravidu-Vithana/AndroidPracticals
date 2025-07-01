package com.ryvk.unittesting.repository

import com.ryvk.unittesting.model.User
import java.util.concurrent.atomic.AtomicReference

class FakeUserRepository : UserRepository {
    private val users = AtomicReference<MutableMap<String, User>>(mutableMapOf())

    override fun save(user: User): User {
        users.getAndUpdate { current ->
            current.toMutableMap().apply {
                this[user.id] = user
            }
        }
        return user
    }

    override fun findById(id: String): User? {
        return users.get()[id]
    }

    override fun findByUsername(username: String): User? {
        return users.get().values.find { it.username == username }
    }

    override fun findAll(): List<User> {
        return users.get().values.toList()
    }

    override fun delete(id: String): Boolean {
        val userExists = users.get().containsKey(id)
        users.getAndUpdate { current ->
            current.toMutableMap().apply {
                remove(id)
            }
        }
        return userExists
    }

    fun clear() {
        users.set(mutableMapOf())
    }
}