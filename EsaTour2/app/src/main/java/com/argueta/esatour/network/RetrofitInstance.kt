package com.argueta.esatour.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "http://192.168.1.2:3000"
object RetrofitInstance {
    private val interceptorLogging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    private var token = ""

    fun setToken(token: String) {
        this.token = token
    }
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(
            OkHttpClient()
                .newBuilder()
                .addInterceptor{ chain ->
                    chain.proceed(
                        chain
                            .request()
                            .newBuilder()
                            .addHeader("Authorization", "Bearer $token")
                            .build()
                    )
                }
                .addInterceptor(interceptorLogging)
                .build()
        )
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    fun getDestinationService(): DestinationService {
        return retrofit.create(DestinationService::class.java)
    }

}