package com.example.mvvm_aplication.domain.model

sealed class Status {
    object Success : Status()
    object Failure : Status()
    object Loading : Status()
}