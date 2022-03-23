package com.example.mvvm_aplication.domain.usecase

import com.example.mvvm_aplication.domain.model.UserLoginInfo
import com.example.mvvm_aplication.domain.repository.UserRepository

class LoginUseCase(private val userRepository: UserRepository) {

    fun execute(userLoginInfo: UserLoginInfo): Boolean {
        userRepository.login(userLoginInfo)
        return true
    }
}