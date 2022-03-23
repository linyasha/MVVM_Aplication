package com.example.mvvm_aplication.data.storage

import com.example.mvvm_aplication.data.storage.model.User

interface UserStorage {

    fun save(user: User)

    fun get(): User

    fun logout()
}