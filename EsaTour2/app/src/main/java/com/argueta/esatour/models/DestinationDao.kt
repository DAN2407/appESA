package com.argueta.esatour.models

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface DestinationDao {
    @Query("SELECT * FROM  destination_table")
    fun getAllDestinations(): List<Destination>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertDestination(destination: List<Destination>)

}