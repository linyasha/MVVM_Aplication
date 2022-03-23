package com.example.mvvm_aplication.data.repository

import com.example.mvvm_aplication.data.storage.UserStorage
import com.example.mvvm_aplication.data.storage.model.User
import com.example.mvvm_aplication.domain.model.UserLoginInfo
import com.example.mvvm_aplication.domain.model.UserRegisterInfo
import com.example.mvvm_aplication.domain.repository.UserRepository

class UserRepositoryImpl(private val userStorage: UserStorage): UserRepository {

    override fun login(userLoginInfo: UserLoginInfo) {

    }

    override fun registration(userRegisterInfo: UserRegisterInfo) {
        userStorage.save(User.map(userRegisterInfo))
    }

    override fun logout() {
        userStorage.logout()
    }

}