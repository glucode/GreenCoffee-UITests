package com.example.greencoffeeuitests.database

import com.example.greencoffeeuitests.model.User
import java.util.*

class UserDatabase {
    fun isValid(username: String, password: String): Boolean {
        for (user in USERS) {
            if (user.matches(username, password)) {
                return true
            }
        }
        return false
    }

    companion object {
        @JvmField
        val USER_1 = User("admin", "1234")
        @JvmField
        val USER_2 = User("guest", "qwerty")
        @JvmField
        val USER_3 = User("root", "11111")
        private val USERS: List<User> = ArrayList(Arrays.asList(
                USER_1,
                USER_2,
                USER_3
        ))
    }
}