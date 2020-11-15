package com.example.smartparking.ui.selecttime

import com.example.smartparking.data.request.FindParkingRequest
import com.example.smartparking.data.response.FindParkingResponse

interface SelectTimeView {
    fun goToLocationList(findParkingRequest: FindParkingRequest?)
}