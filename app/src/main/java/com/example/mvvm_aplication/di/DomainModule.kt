package com.example.mvvm_aplication.di

import com.example.mvvm_aplication.domain.usecase.LoginUseCase
import com.example.mvvm_aplication.domain.usecase.RegisterUseCase
import org.koin.dsl.module

val domainModule = module {

    factory<LoginUseCase> {
        LoginUseCase(userRepository = get())
    }

    factory<RegisterUseCase> {
        RegisterUseCase(userRepository = get())
    }

}
