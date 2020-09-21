package com.example.smartparking.di

import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@InstallIn(ApplicationComponent::class)
@Module
object FirebaseModule {
    @Provides
    fun provideFirebaseAuth() = FirebaseAuth.getInstance()
}