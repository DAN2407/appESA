package com.argueta.esatour.ui.destination

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.argueta.esatour.DestinationUiState
import com.argueta.esatour.network.ApiResponse
import com.argueta.esatour.repository.DestinationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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
    }}