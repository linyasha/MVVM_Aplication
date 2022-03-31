package com.example.mvvm_aplication.domain.model

sealed class Status {
    object Success : Status()
    data class Failure(val errorMessage: String?) : Status()
    object Loading : Status()
}