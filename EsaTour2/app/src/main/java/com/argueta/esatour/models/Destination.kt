package com.argueta.esatour.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "destination_table")
data class Destination (
    @PrimaryKey @ColumnInfo(name = "name ") @SerializedName("name") val name : String,
    @ColumnInfo(name = "description ") @SerializedName("description ") val description : String,
    @ColumnInfo(name = "photo") @SerializedName("photo") val photo : String,
    @ColumnInfo(name = "ubication") @SerializedName("ubication") val ubication : String,
    @ColumnInfo(name = "rating") @SerializedName("rating ") val rating : String,
    @ColumnInfo(name = "category") @SerializedName("category") val category: String,
)