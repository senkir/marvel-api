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
        @Query("limit") limit: Int = 30
    ): ComicDataWrapper
}

@Serializable
data class ComicDataWrapper(
    val code: Int,
    val status: String?,
    val data: ComicDataContainer?,
    val etag: String?
)

@Serializable
data class ComicDataContainer(
    val offset: Int?,
    val limit: Int?,
    val total: Int?,
    val count: Int?,
    val results: List<Comic>?
)

@Serializable
data class Comic(
    val id: Int,
    val digitalId: Int? = null,
    val title: String? = null,
    val issueNumber: Double? = null,
    val variantDescription: String? = null,
    val description: String?= null,
    val modified: String?= null,
    val isbn: String?= null,
    val upc: String?= null,
    val thumbnail: Image?= null
)

@Serializable
data class Image(
    val path: String?,
    val extension: String?
)