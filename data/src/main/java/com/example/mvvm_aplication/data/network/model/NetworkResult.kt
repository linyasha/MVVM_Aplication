package com.example.mvvm_aplication.data.network.model

import com.google.firebase.auth.FirebaseUser
import java.lang.Exception

data class NetworkResult(
    val success: Boolean,
    val throwable: Exception?,
    val user: FirebaseUser? = null
)