package com.example.mvvm_aplication.domain.model

import java.lang.Exception

sealed class Status {
    object Success : Status()
    data class Failure(val exception: Exception?) : Status()
    object Loading : Status()
}