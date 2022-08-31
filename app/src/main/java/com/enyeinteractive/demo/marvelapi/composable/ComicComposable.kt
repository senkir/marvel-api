package com.enyeinteractive.demo.marvelapi.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.enyeinteractive.demo.marvelapi.viewmodel.MarvelRepository

@Composable
fun ComicView(data: MarvelRepository.ComicViewData) {
    //TODO: load image with glide
    //Image()

    Box(modifier = Modifier.background(Color.Green))
    Text(data.title, style = MaterialTheme.typography.body1)
}