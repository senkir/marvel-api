package com.enyeinteractive.demo.marvelapi.viewmodel

import androidx.lifecycle.ViewModel

class MarvelViewModel : ViewModel() {

    private val marvelRepository = MarvelRepository()

// region public

    suspend fun loadData() = marvelRepository.getComics()

// endregion

// region private
// endregion

}