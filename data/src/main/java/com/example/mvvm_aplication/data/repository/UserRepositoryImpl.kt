package com.example.mvvm_aplication.data.repository

import android.util.Log
import com.example.mvvm_aplication.data.network.Network
import com.example.mvvm_aplication.domain.model.Status
import com.example.mvvm_aplication.data.network.model.UserCreds
import com.example.mvvm_aplication.data.storage.UserStorage
import com.example.mvvm_aplication.domain.model.UserLoginInfo
import com.example.mvvm_aplication.domain.model.UserRegisterInfo
import com.example.mvvm_aplication.domain.repository.UserRepository
import kotlinx.coroutines.CompletableDeferred

class UserRepositoryImpl(private val userStorage: UserStorage, private val network: Network): UserRepository {

    override suspend fun login(userLoginInfo: UserLoginInfo): Status {
        //TODO(Save user to DB userStorage)
        val result = network.login(UserCreds(
            email = userLoginInfo.email,
            password = userLoginInfo.password
        ))
        val user = result?.user
        return if (user != null) {
            Status.Success
        } else {
            Status.Failure
        }
    }

    override suspend fun registration(userRegisterInfo: UserRegisterInfo): Status {
        //TODO(Save user to DB userStorage)
        val result = network.register(UserCreds(
            email = userRegisterInfo.email,
            password = userRegisterInfo.password
        ))
        val user = result?.user
        return if (user != null) {
            Status.Success
        } else {
            Status.Failure
        }
    }

    override fun logout() {
        //userStorage.logout()
    }

}