package com.enyeinteractive.demo.marvelapi.viewmodel

import com.enyeinteractive.demo.marvelapi.BuildConfig
import com.enyeinteractive.demo.marvelapi.network.ComicDataWrapper
import com.enyeinteractive.demo.marvelapi.network.MarvelApiService
import com.enyeinteractive.demo.marvelapi.network.Md5Helper
import java.util.Calendar

class MarvelRepository(private val service: MarvelApiService) {


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
                val extension = rawComic.thumbnail?.extension
                val path = rawComic.thumbnail?.path
                list.add(
                    ComicViewData(
                        id = rawComic.isbn ?: "",
                        title = rawComic.title ?: "",
                        description = rawComic.description ?: "",
                        imageUrl = "$path.$extension"
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
        val description: String,
        val imageUrl: String
    )
}