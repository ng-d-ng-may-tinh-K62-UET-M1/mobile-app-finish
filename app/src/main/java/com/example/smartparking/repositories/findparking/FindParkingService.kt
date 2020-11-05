package com.example.smartparking.repositories.findparking

import com.example.smartparking.data.request.FindParkingRequest
import com.example.smartparking.data.response.FindParkingResponse
import com.example.smartparking.utils.PATH_FIND_PARKING
import retrofit2.Response
import retrofit2.http.*

interface FindParkingService {
    @Headers("Content-Type: application/json")
    @POST(PATH_FIND_PARKING)
    suspend fun getParkingListAvailable(@Body request: FindParkingRequest) : Response<FindParkingResponse>
}