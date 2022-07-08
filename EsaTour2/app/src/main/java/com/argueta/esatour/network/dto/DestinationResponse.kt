package com.argueta.esatour.network.dto

import com.argueta.esatour.models.Destination
import com.google.gson.annotations.SerializedName

data class DestinationResponse (
     @SerializedName("destinations")
        val destinations: List<Destination>
)