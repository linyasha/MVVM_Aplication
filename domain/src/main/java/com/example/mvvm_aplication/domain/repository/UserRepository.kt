package com.example.mvvm_aplication.domain.repository

import com.example.mvvm_aplication.domain.model.Status
import com.example.mvvm_aplication.domain.model.UserLoginInfo
import com.example.mvvm_aplication.domain.model.UserRegisterInfo
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun login(userLoginInfo: UserLoginInfo): Flow<Status>

    suspend fun registration(userRegisterInfo: UserRegisterInfo): Flow<Status>

    fun logout()
}