package com.enyeinteractive.demo.marvelapi.viewmodel

import com.enyeinteractive.demo.marvelapi.BuildConfig
import com.enyeinteractive.demo.marvelapi.network.ComicDataWrapper
import com.enyeinteractive.demo.marvelapi.network.MarvelApiService
import com.enyeinteractive.demo.marvelapi.network.Md5Helper
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import okhttp3.MediaType
import retrofit2.Retrofit
import java.util.Calendar

class MarvelRepository {

    private val contentType = MediaType.parse("application/json")


    private val json = Json {
        ignoreUnknownKeys = true
    }

    @OptIn(ExperimentalSerializationApi::class)
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://gateway.marvel.com")
        .addConverterFactory(json.asConverterFactory(contentType!!))
        .build()

    private val service: MarvelApiService = retrofit
        .create(MarvelApiService::class.java)

    // region public
    suspend fun getComics(): List<ComicViewData> {
        val timestamp = Calendar.getInstance().time.time
        return service.getComics(
            timestamp = timestamp.toString(),
            apiKey = BuildConfig.MARVEL_PUBLIC_KEY,
            hash = Md5Helper.md5(timestamp)
        ).toComicViewData()
    }
// endregion

// region private

    private fun ComicDataWrapper?.toComicViewData(): List<ComicViewData> {
        val count = this?.data?.count ?: 0
        return if (count > 0) {
            val list = mutableListOf<ComicViewData>()
            this?.data?.results?.forEach { rawComic ->
                list.add(
                    ComicViewData(
                        id = rawComic.isbn ?: "",
                        title = rawComic.title ?: "",
                        imageUrl = rawComic.thumbnail?.path ?: ""
                    )
                )
            }
            list
        } else {
            emptyList()
        }
    }

// endregion

    data class ComicViewData(
        val id: String,
        val title: String,
        val imageUrl: String
    )
}