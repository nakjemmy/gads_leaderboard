package com.nakjemmy.leaderboard.service

import com.google.gson.GsonBuilder
import com.nakjemmy.leaderboard.API_BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitServiceBuilder {
    private val gson by lazy {
        GsonBuilder()
        .setLenient()
        .create()
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl(API_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    fun <T> buildService(service: Class<T>): T{
        return retrofit.create(service)
    }
}