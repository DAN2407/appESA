package com.argueta.esatour

import android.app.Application
import android.content.SharedPreferences
import com.argueta.esatour.models.EsaTourDatabase
import com.argueta.esatour.network.RetrofitInstance
import com.argueta.esatour.repository.DestinationRepository
import com.argueta.esatour.repository.LoginRepository

class EsaTourApplication : Application() {
    private val prefs: SharedPreferences by lazy {
        getSharedPreferences("ESATour", MODE_PRIVATE)

    }
    private val dataBase by lazy{
        EsaTourDatabase.getInstance(this)
    }

    private fun getApiService() = with(RetrofitInstance){
        setToken(getToken())
        getDestinationService()
    }

    fun getDestinationRepository() =
        DestinationRepository(dataBase, getApiService())

    fun getLoginRepository() =
        LoginRepository(getApiService())

    private fun getToken() : String = prefs.getString(USER_TOKEN, "")!!

    fun isUserLogin() = getToken() != ""

    fun saveAuthToken(token: String){
        val editor = prefs.edit()
        editor.putString(USER_TOKEN, token)
        editor.apply()

    }


    companion object{
        const val USER_TOKEN = "user_token"
    }
}

