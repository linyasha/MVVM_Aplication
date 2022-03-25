package com.example.mvvm_aplication.domain.usecase

import com.example.mvvm_aplication.domain.model.UserLoginInfo
import com.example.mvvm_aplication.domain.repository.UserRepository

class LoginUseCase(private val userRepository: UserRepository) {

    suspend fun execute(userLoginInfo: UserLoginInfo) = userRepository.login(userLoginInfo)

}