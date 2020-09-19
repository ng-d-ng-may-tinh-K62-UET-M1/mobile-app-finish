package com.example.smartparking.di

import com.example.smartparking.utils.FirebaseAuthSignInHandler
import com.example.smartparking.utils.SignInHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.coroutines.CoroutineScope

@InstallIn(ApplicationComponent::class)
@Module
object SignInModule {
    @Provides
    fun provideSignInHandler(@ApplicationScope applicationScope: CoroutineScope) : FirebaseAuthSignInHandler = FirebaseAuthSignInHandler(applicationScope)
}