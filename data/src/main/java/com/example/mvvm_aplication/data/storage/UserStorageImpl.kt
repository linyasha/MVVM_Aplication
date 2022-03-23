package com.example.mvvm_aplication.data.storage

import com.example.mvvm_aplication.data.storage.model.User

class UserStorageImpl: UserStorage {

    override fun save(user: User) {
        TODO("Go to DB and save user")
    }

    override fun get(): User {
        TODO("Go to DB and return user")
    }

    override fun logout() {
        TODO("Not yet implemented")
    }
}