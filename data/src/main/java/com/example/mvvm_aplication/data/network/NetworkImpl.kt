package com.example.mvvm_aplication.data.network

import android.util.Log
import com.example.mvvm_aplication.data.network.model.NetworkResult
import com.example.mvvm_aplication.data.network.model.UserCreds
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.*
import kotlinx.coroutines.tasks.await

class NetworkImpl(private val fireBase: FirebaseAuth): Network {

    private val networkScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    override suspend fun login(userCreds: UserCreds): NetworkResult =
        try {
            var errorMessage: String? = null
            var isLoginSuccess = false
            val result = fireBase.signInWithEmailAndPassword(userCreds.email, userCreds.password)
                .addOnCompleteListener {
                    isLoginSuccess = true
                }
                .addOnFailureListener {
                    errorMessage = it.message
                }.await()
            NetworkResult(isLoginSuccess, errorMessage, result.user)
        } catch (e: Exception) {
            NetworkResult(false, e.message)
        }

    override suspend fun register(userCreds: UserCreds): NetworkResult =
        try {
            var errorMessage: String? = null
            var isUserRegister = false
            val result = fireBase.createUserWithEmailAndPassword(userCreds.email, userCreds.password)
                .addOnCompleteListener {
                    isUserRegister = true
                }
                .addOnFailureListener {
                    errorMessage = it.message
                    isUserRegister = false
                }.await()
            NetworkResult(isUserRegister, errorMessage, result.user)
        } catch (e: Exception) {
            NetworkResult(false, e.message)
        }
}

