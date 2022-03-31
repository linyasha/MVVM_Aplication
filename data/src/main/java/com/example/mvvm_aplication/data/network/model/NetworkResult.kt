package com.example.mvvm_aplication.data.network.model

import com.google.firebase.auth.FirebaseUser

data class NetworkResult(
    val success: Boolean,
    val errorMessage: String?,
    val user: FirebaseUser? = null
)