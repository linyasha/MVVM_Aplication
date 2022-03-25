package com.example.mvvm_aplication.di

import com.example.mvvm_aplication.data.network.Network
import com.example.mvvm_aplication.data.network.NetworkImpl
import com.example.mvvm_aplication.data.repository.UserRepositoryImpl
import com.example.mvvm_aplication.data.storage.UserStorage
import com.example.mvvm_aplication.data.storage.UserStorageImpl
import com.example.mvvm_aplication.domain.repository.UserRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.koin.dsl.module

val dataModule = module {

    single <FirebaseAuth> {
        Firebase.auth
    }

    single<UserStorage> {
        UserStorageImpl()
    }

    single<Network> {
        NetworkImpl(fireBase = get())
    }

    single<UserRepository> {
        UserRepositoryImpl(
            userStorage = get(),
            network = get()
        )
    }


}