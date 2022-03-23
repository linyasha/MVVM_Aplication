package com.example.mvvm_aplication.domain.usecase

import com.example.mvvm_aplication.domain.model.UserLoginInfo
import com.example.mvvm_aplication.domain.model.UserRegisterInfo
import com.example.mvvm_aplication.domain.repository.UserRepository

class RegisterUseCase(private val userRepository: UserRepository) {

    fun execute(user: UserRegisterInfo): Boolean {
        userRepository.registration(user)
        return true
    }

}