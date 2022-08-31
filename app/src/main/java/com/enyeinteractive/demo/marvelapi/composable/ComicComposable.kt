package com.enyeinteractive.demo.marvelapi.composable

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.enyeinteractive.demo.marvelapi.viewmodel.MarvelRepository

@Composable
fun ComicView(data: MarvelRepository.ComicViewData) {

    val placeholder = ColorPainter(Color.Gray)
    Row(modifier = Modifier.padding(8.dp).height(150.dp)) {
        val picture = loadPicture(data.imageUrl, placeholder)
        Image(
            painter = picture, contentDescription = "thumbnail",
            modifier = Modifier.fillMaxHeight()
        )
        Column {

            Text(
                text = data.title,
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 4.dp)
            )

            Text(
                text = data.description,
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier.padding(horizontal = 4.dp)
            )
        }
    }

}

@Composable
fun loadPicture(url: String, placeholder: Painter): Painter {
    val state = remember {
        mutableStateOf(placeholder)
    }

    val context = LocalContext.current

    val result = object : CustomTarget<Bitmap>(500, 800) {
        override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
            state.value = BitmapPainter(resource.asImageBitmap())
            Log.d("test", "onResourceReady")
        }

        override fun onLoadCleared(d: Drawable?) {
            state.value = placeholder
            Log.d("test", "onLoadCleared")
        }
    }

    LaunchedEffect(url) {

        try {
            Glide.with(context)
                .asBitmap()
                .load(url)
                .into(result)
        } catch (e: Exception) {
            //something bad happened during loading
            Log.e("test", "something bad happened")
        }
    }

    return state.value
}