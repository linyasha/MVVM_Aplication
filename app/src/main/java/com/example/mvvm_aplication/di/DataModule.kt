package com.example.mvvm_aplication.di

import com.example.mvvm_aplication.data.repository.UserRepositoryImpl
import com.example.mvvm_aplication.data.storage.UserStorage
import com.example.mvvm_aplication.data.storage.UserStorageImpl
import com.example.mvvm_aplication.domain.repository.UserRepository
import org.koin.dsl.module

val dataModule = module {

    single<UserStorage> {
        UserStorageImpl()
    }

    single<UserRepository> {
        UserRepositoryImpl(userStorage = get())
    }

}