package com.enyeinteractive.demo.marvelapi.viewmodel

import com.enyeinteractive.demo.marvelapi.network.ComicDataWrapper
import com.enyeinteractive.demo.marvelapi.network.MarvelApiService
import com.enyeinteractive.demo.marvelapi.network.Md5Helper
import retrofit2.Retrofit
import java.util.Calendar

class MarvelRepository {

    private val service: MarvelApiService = Retrofit.Builder()
        .baseUrl("https://gateway.marvel.com").build()
        .create(MarvelApiService::class.java)

    // region public
    suspend fun getComics(): List<ComicViewData> {
        val timestamp = Calendar.getInstance().time.time
        return service.getComics(
            timestamp = timestamp.toString(),
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

// region overrides
// endregion

// region companion
// endregion

    data class ComicViewData(
        val id: String,
        val title: String,
        val imageUrl: String
    )
}