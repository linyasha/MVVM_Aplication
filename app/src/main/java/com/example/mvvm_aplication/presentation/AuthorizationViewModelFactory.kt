package com.example.mvvm_aplication.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm_aplication.data.repository.UserRepositoryImpl
import com.example.mvvm_aplication.data.storage.UserStorageImpl
import com.example.mvvm_aplication.domain.usecase.LoginUseCase
import com.example.mvvm_aplication.domain.usecase.RegisterUseCase

class AuthorizationViewModelFactory: ViewModelProvider.Factory {

    private val loginUseCase = LoginUseCase(userRepository = UserRepositoryImpl(UserStorageImpl()))
    private val registerUseCase = RegisterUseCase(userRepository = UserRepositoryImpl(UserStorageImpl()))

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AuthorizationViewModel(
            loginUseCase = loginUseCase,
            registerUseCase = registerUseCase
        ) as T
    }
}