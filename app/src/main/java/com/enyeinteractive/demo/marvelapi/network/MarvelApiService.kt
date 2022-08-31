package com.enyeinteractive.demo.marvelapi.network

import com.enyeinteractive.demo.marvelapi.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface MarvelApiService {

    @Headers(
        "apikey: ${BuildConfig.MARVEL_PUBLIC_KEY}"
    )
    @GET("v1/public/comics")
    suspend fun getComics(
        @Header("ts") timestamp: String,
        @Header("hash") hash: String,
        @Query("limit") limit: Int = 10
    ): ComicDataWrapper
}

data class ComicDataWrapper(
    val code: Int, //The HTTP status code of the returned result.,
    val status: String?,// (string, optional): A string description of the call status.,
    val data: ComicDataContainer?,// (ComicDataContainer, //optional ) : The results returned by the call.,
    val etag: String?// (string, optional): A digest value of the content returned by the call.
)

data class ComicDataContainer(
    val offset: Int?,//(int, optional): The requested offset (number of skipped results) of the call.,
    val limit: Int?,//(int, optional): The requested result limit.,
    val total: Int?,//(int, optional): The total number of resources available given the current filter set.,
    val count: Int?,//(int, optional): The total number of results returned by this call.,
    val results: Array<Comic>? //(Array[Comic], optional): The list of comics returned by the call
)

data class Comic(
    val id: Int,//(int, optional): The unique ID of the comic resource.,
    val digitalId: Int?,
    val title: String?,//(string, optional): The canonical title of the comic.,
    val issueNumber: Double?,
    val variantDescription: String?,
    val description: String?,
    val modified: String?,
    val isbn: String?,
    val upc: String?,
    val thumbnail: Image?// (Image, optional): The representative image for this comic.,
)

data class Image(
    val path: String?,// (string, optional): The directory path of to the image.,
    val extension: String?// (string, optional): The file extension for the image.
)