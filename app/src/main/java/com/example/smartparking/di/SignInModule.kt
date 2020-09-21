package com.example.smartparking.di

import android.content.Context
import com.example.smartparking.R
import com.example.smartparking.utils.auth.AuthenticationManager
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object SignInModule {
    @Provides
    fun provideGoogleSignInOptions(@ApplicationContext context: Context) =
        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(context.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

    @Provides
    fun provideGoogleSignInClient(
        @ApplicationContext context: Context,
        googleSignInOptions: GoogleSignInOptions
    ) = GoogleSignIn.getClient(context, googleSignInOptions)

    @Provides
    fun provideAuthenticationManager(
        firebaseAuth: FirebaseAuth,
        googleSignInClient: GoogleSignInClient
    ) =
        AuthenticationManager(
            firebaseAuth,
            googleSignInClient
        )
}