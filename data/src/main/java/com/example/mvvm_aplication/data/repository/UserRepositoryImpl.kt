package com.example.mvvm_aplication.data.repository

import android.util.Log
import com.example.mvvm_aplication.data.network.Network
import com.example.mvvm_aplication.domain.model.Status
import com.example.mvvm_aplication.data.network.model.UserCreds
import com.example.mvvm_aplication.data.storage.UserStorage
import com.example.mvvm_aplication.domain.model.UserLoginInfo
import com.example.mvvm_aplication.domain.model.UserRegisterInfo
import com.example.mvvm_aplication.domain.repository.UserRepository
import com.example.mvvm_aplication.mappers.networkResultToStatus
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserRepositoryImpl(private val userStorage: UserStorage, private val network: Network): UserRepository {

    override suspend fun login(userLoginInfo: UserLoginInfo): Flow<Status>  = flow {
        emit(Status.Loading)
        val result = network.login(UserCreds(
            email = userLoginInfo.email,
            password = userLoginInfo.password
        ))
        //TODO(Save user to DB userStorage)
        val user = result.user
        emit(networkResultToStatus(result))
    }

    override suspend fun registration(userRegisterInfo: UserRegisterInfo): Flow<Status> = flow {
        emit(Status.Loading)
        val result = network.register(UserCreds(
            email = userRegisterInfo.email,
            password = userRegisterInfo.password
        ))
        //TODO(Save user to DB userStorage)
        val user = result.user
        emit(networkResultToStatus(result))
    }

    override fun logout() {
        //userStorage.logout()
    }

}