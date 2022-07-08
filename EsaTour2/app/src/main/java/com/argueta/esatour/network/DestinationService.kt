package com.argueta.esatour.network

import com.argueta.esatour.network.dto.DestinationResponse
import com.argueta.esatour.network.dto.LoginRequest
import com.argueta.esatour.network.dto.LoginResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface DestinationService {
    @GET("/api/destinations")
    suspend fun getAllDestinations(): DestinationResponse

    @POST("/login")
    suspend fun login(@Body credentials: LoginRequest): LoginResponse

    @POST("/register")
    suspend fun register(@Body credentials: LoginRequest): LoginResponse
}