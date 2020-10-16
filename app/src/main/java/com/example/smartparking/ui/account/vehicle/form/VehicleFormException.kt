package com.example.smartparking.ui.account.vehicle.form

import androidx.annotation.StringRes

sealed class VehicleFormException(@StringRes fieldName: Int) : Throwable() {
    data class MissingRequiredFieldException(@StringRes val fieldName: Int) : VehicleFormException(fieldName)
}