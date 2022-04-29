package com.example.mvvm_aplication.mappers

import com.example.mvvm_aplication.data.network.model.NetworkResult
import com.example.mvvm_aplication.domain.model.Status

fun networkResultToStatus(result: NetworkResult): Status {
    return if (result.success) {
        Status.Success
    } else {
        Status.Failure(result.throwable)
    }
}