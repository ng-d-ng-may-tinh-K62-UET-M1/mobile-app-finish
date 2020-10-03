package com.example.smartparking.di

import com.example.smartparking.ui.account.AccountController
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@InstallIn(ApplicationComponent::class)
@Module
object ControllerModule {
    @Provides
    fun provideAccountController() = AccountController()
}