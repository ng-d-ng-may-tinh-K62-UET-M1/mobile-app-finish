package com.example.smartparking.ui.myparking

import androidx.annotation.ColorInt

interface MyParkingDescription {
    fun getNumberPlate() : String
    fun statusName() : String
    @ColorInt fun getStatusColor() : Int
    fun getTimeIn(): String
    fun getTimeOut() : String
}