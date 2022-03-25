package com.example.mvvm_aplication.domain.repository

import com.example.mvvm_aplication.domain.model.Status
import com.example.mvvm_aplication.domain.model.UserLoginInfo
import com.example.mvvm_aplication.domain.model.UserRegisterInfo

interface UserRepository {
    suspend fun login(userLoginInfo: UserLoginInfo): Status

    suspend fun registration(userRegisterInfo: UserRegisterInfo): Status

    fun logout()
}