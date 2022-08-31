package com.enyeinteractive.demo.marvelapi.network

import kotlinx.serialization.Serializable
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApiService {

    @GET("v1/public/comics")
    suspend fun getComics(
        @Query("ts") timestamp: String,
        @Query("hash") hash: String,
        @Query("apikey") apiKey: String,
        @Query("limit") limit: Int = 10
    ): ComicDataWrapper
}

@Serializable
data class ComicDataWrapper(
    val code: Int, //The HTTP status code of the returned result.,
    val status: String?,// (string, optional): A string description of the call status.,
    val data: ComicDataContainer?,// (ComicDataContainer, //optional ) : The results returned by the call.,
    val etag: String?// (string, optional): A digest value of the content returned by the call.
)

@Serializable
data class ComicDataContainer(
    val offset: Int?,//(int, optional): The requested offset (number of skipped results) of the call.,
    val limit: Int?,//(int, optional): The requested result limit.,
    val total: Int?,//(int, optional): The total number of resources available given the current filter set.,
    val count: Int?,//(int, optional): The total number of results returned by this call.,
    val results: Array<Comic>? //(Array[Comic], optional): The list of comics returned by the call
)

@Serializable
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

@Serializable
data class Image(
    val path: String?,// (string, optional): The directory path of to the image.,
    val extension: String?// (string, optional): The file extension for the image.
)