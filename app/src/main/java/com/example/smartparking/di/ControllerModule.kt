package com.example.smartparking.di

import com.example.smartparking.ui.account.AccountController
import com.example.smartparking.ui.account.paymentmethod.PaymentMethodController
import com.example.smartparking.ui.account.vehicle.list.VehicleListController
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@InstallIn(ApplicationComponent::class)
@Module
object ControllerModule {
    @Provides
    fun provideAccountController() = AccountController()

    @Provides
    fun provideVehicleListController() = VehicleListController()

    @Provides
    fun providePaymentMethodListController() = PaymentMethodController()
}