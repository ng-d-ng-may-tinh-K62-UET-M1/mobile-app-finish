package com.example.smartparking.di

import android.app.Activity
import android.content.Context
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.smartparking.R
import com.example.smartparking.data.preference.PreferenceDataSource
import com.example.smartparking.utils.auth.AuthenticationManager
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object AppModule {
    @Singleton
    @Provides
    fun providePreferenceDataSource(@ApplicationContext context: Context): PreferenceDataSource =
        PreferenceDataSource(context)
}