package com.example.mvvm_aplication.presentation

import androidx.lifecycle.ViewModel
import com.example.mvvm_aplication.domain.model.UserLoginInfo
import com.example.mvvm_aplication.domain.model.UserRegisterInfo
import com.example.mvvm_aplication.domain.usecase.LoginUseCase
import com.example.mvvm_aplication.domain.usecase.RegisterUseCase

class AuthorizationViewModel(
    private val loginUseCase: LoginUseCase,
    private val registerUseCase: RegisterUseCase): ViewModel() {

    fun login(userLoginInfo: UserLoginInfo) {
        loginUseCase.execute(userLoginInfo = userLoginInfo)
    }

    fun registration(userRegisterInfo: UserRegisterInfo) {
        registerUseCase.execute(userRegisterInfo)
    }
}