package com.argueta.esatour.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.argueta.esatour.EsaTourApplication
import com.argueta.esatour.MainActivity
import com.argueta.esatour.R
import com.argueta.esatour.databinding.ActivityLoginBinding
import com.argueta.esatour.ui.ViewModelFactory

class LoginActivity: AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    private val app by lazy {
        application as EsaTourApplication
    }

    private val viewModelFactory by lazy {
        ViewModelFactory(app.getLoginRepository())
    }

    private val viewModel: LoginViewModel by viewModels{
        viewModelFactory
    }

    override fun onCreate(saveInstanceState: Bundle?) {
        super.onCreate(saveInstanceState)
        if (app.isUserLogin()){
            return startMainActivity()
        }
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.viewModel = viewModel

        viewModel.status.observe(this) { status ->
            handleUiState(status)
        }
    }
    private fun handleUiState(status: LoginUiStatus) {
        when (status) {
            is LoginUiStatus.Error -> Log.d("login list status", "Error")
            LoginUiStatus.Loading -> Log.d("login list status", "Loading")
            LoginUiStatus.Resume -> Log.d("login list status", "Resume")
            is LoginUiStatus.Success -> {
                app.saveAuthToken(status.token)
                startMainActivity()
            }
        }
    }
    private fun startMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}

