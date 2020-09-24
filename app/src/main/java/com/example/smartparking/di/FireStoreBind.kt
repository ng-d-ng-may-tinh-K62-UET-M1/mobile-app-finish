package com.example.smartparking.di

import com.example.smartparking.firestore.user.FireStoreUser
import com.example.smartparking.firestore.user.FireStoreUserImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class FireStoreBind {
    @Binds
    abstract fun provideFireStoreUser(impl: FireStoreUserImp): FireStoreUser


}