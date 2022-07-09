package com.argueta.esatour

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.argueta.esatour.models.Destination

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}



    sealed class DestinationUiState {
        object Loading : DestinationUiState()
        data class Success(val data: List<Destination>) : DestinationUiState() {
            val destinations: List<Destination>
                get() = data
        }

        data class Error(val exception: Exception) : DestinationUiState()
    }
