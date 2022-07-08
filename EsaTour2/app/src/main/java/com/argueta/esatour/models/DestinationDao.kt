package com.argueta.esatour.models

import androidx.room.*


@Dao
interface DestinationDao {
    @Query("SELECT * FROM  destination_table")
    fun getAllDestinations(): List<Destination>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertWord(destination: Destination)

    @Update
    suspend fun updateWord(destination: Destination)

    @Delete
    suspend fun deleteWord(destination: Destination)
}