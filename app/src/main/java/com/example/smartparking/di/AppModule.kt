package com.example.smartparking.di

import android.content.Context
import com.example.smartparking.data.preference.PreferenceDataSource
import com.example.smartparking.utils.AuthenticationManager
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object AppModule {
    @Singleton
    @Provides
    fun providePreferenceDataSource(@ApplicationContext context: Context): PreferenceDataSource = PreferenceDataSource(context)

    @Singleton
    @Provides
    fun provideAuthenticationManager(@ApplicationContext context: Context, firebaseAuth: FirebaseAuth) = AuthenticationManager(context, firebaseAuth)
}