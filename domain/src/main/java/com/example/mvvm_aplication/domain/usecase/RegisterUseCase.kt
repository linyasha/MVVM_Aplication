package com.example.mvvm_aplication.domain.usecase

import com.example.mvvm_aplication.domain.model.UserRegisterInfo
import com.example.mvvm_aplication.domain.repository.UserRepository

class RegisterUseCase(private val userRepository: UserRepository) {

    suspend fun execute(user: UserRegisterInfo) = userRepository.registration(user)

}