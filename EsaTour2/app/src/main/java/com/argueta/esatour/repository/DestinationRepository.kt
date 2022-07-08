package com.argueta.esatour.repository

import com.argueta.esatour.models.Destination
import com.argueta.esatour.models.EsaTourDatabase
import com.argueta.esatour.network.ApiResponse
import com.argueta.esatour.network.DestinationService
import retrofit2.HttpException
import java.io.IOException

class DestinationRepository (
    database: EsaTourDatabase,
    private val api: DestinationService
) {

    private val destinationDoa = database.destinationDao()

    suspend fun getAllDestinations(): ApiResponse<List<Destination>> {
        return try {
            val response = api.getAllDestinations()
            if(response.destinations.isEmpty()) {
                destinationDoa.insertDestination(response.destinations)
            }
            ApiResponse.Success(data = destinationDoa.getAllDestinations())
        } catch (e: HttpException) {
            ApiResponse.Error(e)
        } catch (e: IOException) {
            ApiResponse.Error(e)
        }
    }

}
