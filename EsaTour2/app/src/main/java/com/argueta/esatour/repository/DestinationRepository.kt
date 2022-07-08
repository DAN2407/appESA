package com.argueta.esatour.repository

import com.argueta.esatour.models.Destination
import com.argueta.esatour.network.ApiResponse
import com.argueta.esatour.network.DestinationService
import retrofit2.HttpException
import java.io.IOException

class DestinationRepository (
    private val api: DestinationService
) {

    suspend fun getAllDestinations(): ApiResponse<List<Destination>> {
         try {
            val response = api.getAllDestinations()
             return ApiResponse.Success(response.destinations)


        } catch (e: HttpException) {
            return ApiResponse.Error(e)
        } catch (e: IOException) {
            return ApiResponse.Error(e)
        }
    }

}
