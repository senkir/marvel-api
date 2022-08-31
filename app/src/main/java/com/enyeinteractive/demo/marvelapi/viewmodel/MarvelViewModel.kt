package com.enyeinteractive.demo.marvelapi.viewmodel

import androidx.lifecycle.ViewModel
import com.enyeinteractive.demo.marvelapi.network.MarvelApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit

class MarvelViewModel : ViewModel() {

    private val contentType = MediaType.parse("application/json")

    private val json = Json {
        ignoreUnknownKeys = true
    }

    @OptIn(ExperimentalSerializationApi::class)
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(json.asConverterFactory(contentType!!))
        .build()

    private val service: MarvelApiService = retrofit
        .create(MarvelApiService::class.java)

    private val marvelRepository = MarvelRepository(service)

// region public

    suspend fun loadData() = marvelRepository.getComics()

// endregion


    companion object {
        const val BASE_URL = "https://gateway.marvel.com"
    }
}