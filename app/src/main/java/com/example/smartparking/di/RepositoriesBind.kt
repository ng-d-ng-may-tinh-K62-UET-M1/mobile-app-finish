package com.example.smartparking.di

import com.example.smartparking.repositories.users.UserRepository
import com.example.smartparking.repositories.users.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@InstallIn(ApplicationComponent::class)
@Module
abstract class RepositoriesBind {
    @Binds
    abstract fun provideUserRepository(impl: UserRepositoryImpl): UserRepository
}