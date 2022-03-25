package com.example.mvvm_aplication.data.network

import com.example.mvvm_aplication.data.network.model.UserCreds
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.*
import kotlinx.coroutines.tasks.await

class NetworkImpl(private val fireBase: FirebaseAuth): Network {

    private val networkScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    override suspend fun login(userCreds: UserCreds): AuthResult? {
        return try {
            fireBase.signInWithEmailAndPassword(userCreds.email, userCreds.password).await()
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun register(userCreds: UserCreds): AuthResult? {
        return try {
            fireBase.createUserWithEmailAndPassword(userCreds.email, userCreds.password).await()
        } catch (e: Exception) {
            null
        }
    }

}