package com.example.smartparking.repositories.booking

import com.example.smartparking.data.model.Booking
import com.example.smartparking.utils.Resource
import kotlinx.coroutines.flow.Flow

interface BookingRepository {
    fun getBookingBelongToUserId(uid: String) : Flow<Resource<List<Booking>>>
}