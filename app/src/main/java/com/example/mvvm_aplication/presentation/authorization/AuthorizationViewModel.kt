package com.example.mvvm_aplication.presentation.authorization

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm_aplication.domain.model.Status
import com.example.mvvm_aplication.domain.model.UserLoginInfo
import com.example.mvvm_aplication.domain.model.UserRegisterInfo
import com.example.mvvm_aplication.domain.usecase.LoginUseCase
import com.example.mvvm_aplication.domain.usecase.RegisterUseCase
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class AuthorizationViewModel(
    private val loginUseCase: LoginUseCase,
    private val registerUseCase: RegisterUseCase): ViewModel() {

    private val _authStatus = MutableStateFlow<Status?>(null)
    val authStatus: StateFlow<Status?> get() = _authStatus

    fun login(userLoginInfo: UserLoginInfo) {
        viewModelScope.launch {
            _authStatus.emitAll(loginUseCase.execute(userLoginInfo))
        }
    }

    fun registration(userRegisterInfo: UserRegisterInfo) {
        viewModelScope.launch {
            _authStatus.emitAll(registerUseCase.execute(userRegisterInfo))
        }
    }

}