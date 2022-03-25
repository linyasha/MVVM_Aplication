package com.example.mvvm_aplication.data.network

import com.example.mvvm_aplication.domain.model.Status
import com.example.mvvm_aplication.data.network.model.UserCreds
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

interface Network {

    suspend fun login(userCreds: UserCreds): AuthResult?

    suspend fun register(userCreds: UserCreds): AuthResult?

}