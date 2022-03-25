package com.example.mvvm_aplication.presentation.authorization

import androidx.lifecycle.ViewModel
import com.example.mvvm_aplication.domain.model.Status
import com.example.mvvm_aplication.domain.model.UserLoginInfo
import com.example.mvvm_aplication.domain.model.UserRegisterInfo
import com.example.mvvm_aplication.domain.usecase.LoginUseCase
import com.example.mvvm_aplication.domain.usecase.RegisterUseCase
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.flow.flow

class AuthorizationViewModel(
    private val loginUseCase: LoginUseCase,
    private val registerUseCase: RegisterUseCase): ViewModel() {

    fun login(userLoginInfo: UserLoginInfo) = flow {
        emit(Status.Loading)
        emit(CompletableDeferred(loginUseCase.execute(userLoginInfo = userLoginInfo)).await())
    }

    fun registration(userRegisterInfo: UserRegisterInfo) = flow {
        emit(Status.Loading)
        emit(CompletableDeferred(registerUseCase.execute(userRegisterInfo)).await())
    }

}