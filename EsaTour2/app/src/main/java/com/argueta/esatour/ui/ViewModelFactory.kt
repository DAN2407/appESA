package com.argueta.esatour.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.argueta.esatour.repository.DestinationRepository
import com.argueta.esatour.repository.LoginRepository
import com.argueta.esatour.ui.destination.DestinationViewModel
import com.argueta.esatour.ui.login.LoginViewModel

class ViewModelFactory<R>(private val repository: R) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(DestinationViewModel::class.java)) {
            return DestinationViewModel(repository as DestinationRepository) as T
        }
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(repository as LoginRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}
