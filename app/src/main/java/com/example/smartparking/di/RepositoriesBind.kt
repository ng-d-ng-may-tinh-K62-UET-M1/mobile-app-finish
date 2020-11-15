package com.example.smartparking.di

import com.example.smartparking.repositories.parking.ParkingRepository
import com.example.smartparking.repositories.parking.ParkingRepositoryImpl
import com.example.smartparking.repositories.paymentmethod.PaymentMethodRepository
import com.example.smartparking.repositories.paymentmethod.PaymentMethodRepositoryImpl
import com.example.smartparking.repositories.users.UserRepository
import com.example.smartparking.repositories.users.UserRepositoryImpl
import com.example.smartparking.repositories.vehicles.VehicleRepository
import com.example.smartparking.repositories.vehicles.VehicleRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@InstallIn(ApplicationComponent::class)
@Module
abstract class RepositoriesBind {
    @Binds
    abstract fun provideUserRepository(impl: UserRepositoryImpl): UserRepository

    @Binds
    abstract fun provideVehicleRepository(impl: VehicleRepositoryImpl) : VehicleRepository

    @Binds
    abstract fun providePaymentMethodRepository(impl: PaymentMethodRepositoryImpl) : PaymentMethodRepository

    @Binds
    abstract fun provideParkingRepository(impl: ParkingRepositoryImpl) : ParkingRepository
}