package com.example.mvvm_aplication.data.network

import com.example.mvvm_aplication.data.network.model.NetworkResult
import com.example.mvvm_aplication.data.network.model.UserCreds
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.*
import kotlinx.coroutines.tasks.await

class NetworkImpl(private val fireBase: FirebaseAuth): Network {

    private val networkScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    override suspend fun login(userCreds: UserCreds): NetworkResult =
        try {
            var exception: Exception? = null
            var isLoginSuccess = false
            val result = fireBase.signInWithEmailAndPassword(userCreds.email, userCreds.password)
                .addOnCompleteListener {
                    isLoginSuccess = true
                }
                .addOnFailureListener {
                    exception = it
                }.await()
            NetworkResult(isLoginSuccess, exception, result.user)
        } catch (e: Exception) {
            NetworkResult(false, e)
        }

    override suspend fun register(userCreds: UserCreds): NetworkResult =
        try {
            var exception: Exception? = null
            var isUserRegister = false
            val result = fireBase.createUserWithEmailAndPassword(userCreds.email, userCreds.password)
                .addOnCompleteListener {
                    isUserRegister = true
                }
                .addOnFailureListener {
                    exception = it
                    isUserRegister = false
                }.await()
            NetworkResult(isUserRegister, exception, result.user)
        } catch (e: Exception) {
            NetworkResult(false, e)
        }
}

