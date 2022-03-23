package com.example.mvvm_aplication.domain.repository

import com.example.mvvm_aplication.domain.model.UserLoginInfo
import com.example.mvvm_aplication.domain.model.UserRegisterInfo

interface UserRepository {
    fun login(userLoginInfo: UserLoginInfo)

    fun registration(userRegisterInfo: UserRegisterInfo)

    fun logout()
}