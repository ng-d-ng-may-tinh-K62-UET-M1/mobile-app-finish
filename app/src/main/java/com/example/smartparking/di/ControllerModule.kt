package com.example.smartparking.di

import com.example.smartparking.ui.account.AccountController
import com.example.smartparking.ui.account.paymentmethod.PaymentMethodController
import com.example.smartparking.ui.account.vehicle.list.VehicleListController
import com.example.smartparking.ui.booking.BookingController
import com.example.smartparking.ui.booking.vehicles.BookingVehicleController
import com.example.smartparking.ui.myparking.MyParkingController
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

    @Provides
    fun provideBookingController() = BookingController()

    @Provides
    fun provideBookingVehicleController() = BookingVehicleController()

    @Provides
    fun provideMyParkingController() = MyParkingController()
}