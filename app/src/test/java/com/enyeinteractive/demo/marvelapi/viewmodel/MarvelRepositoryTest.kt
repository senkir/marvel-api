package com.enyeinteractive.demo.marvelapi.viewmodel

import com.enyeinteractive.demo.marvelapi.network.Comic
import com.enyeinteractive.demo.marvelapi.network.ComicDataContainer
import com.enyeinteractive.demo.marvelapi.network.ComicDataWrapper
import com.enyeinteractive.demo.marvelapi.network.MarvelApiService
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test


class MarvelRepositoryTest {

    private val service: MarvelApiService = mockk { }

    private val target = MarvelRepository(service)

    @Test
    fun `comic wrapper result should map to ComicViewData`() {
        generateSampleResult()
        val result = runBlocking {
            target.getComics()
        }
        assertThat(result.first().title).isEqualTo("Comic A")
        assertThat(result.first().description).isEqualTo("stub")
    }

    private fun generateSampleResult() {
        coEvery {
            service.getComics(
                any(),
                any(),
                any()
            )
        } answers {
            ComicDataWrapper(
                code = 200,
                status = "Ok",
                data = ComicDataContainer(
                    offset = 0,
                    limit = 20,
                    total = 20,
                    count = 20,
                    results = listOf(
                        Comic(
                            id = 1,
                            title = "Comic A",
                            description = "stub"
                        )
                    )
                ),
                etag = "4119c881bdad613589ac9506bce22a83e4835d53"
            )
        }
    }
}