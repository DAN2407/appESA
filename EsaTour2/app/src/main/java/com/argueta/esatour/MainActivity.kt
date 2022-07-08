package com.argueta.esatour

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.argueta.esatour.models.Destination
import com.argueta.esatour.network.ApiResponse
import com.argueta.esatour.repository.DestinationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
class DestinationViewModel (private val repository: DestinationRepository): ViewModel() {
    private val _status = MutableLiveData<DestinationUiState>(DestinationUiState.Loading)
    val status: LiveData<DestinationUiState>
    get() = _status

    fun getAllDestinations() {
        viewModelScope.launch(Dispatchers.IO){
            _status.postValue(
                when (val response = repository.getAllDestinations()){
                    is ApiResponse.Success -> DestinationUiState.Success(response.data)
                    is ApiResponse.Error -> DestinationUiState.Error(response.exception)
                    is ApiResponse.ErrorWithMessage -> TODO()

                }
            )
        }
    }
    fun addDestination(destination: Destination){
        viewModelScope.launch(Dispatchers.IO){
            repository.getAllDestinations()
        }
    }

    sealed class DestinationUiState {
        object Loading : DestinationUiState()
        data class Success(val data: LiveData<List<Destination>>) : DestinationUiState()
        data class Error(val exception: Exception) : DestinationUiState()
    }
}