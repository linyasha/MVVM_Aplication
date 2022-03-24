package com.example.mvvm_aplication.di

import com.example.mvvm_aplication.presentation.AuthorizationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel<AuthorizationViewModel> {
        AuthorizationViewModel(
            loginUseCase = get(),
            registerUseCase = get()
        )
    }

}