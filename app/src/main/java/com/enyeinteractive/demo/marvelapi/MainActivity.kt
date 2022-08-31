package com.enyeinteractive.demo.marvelapi

import MainScreen
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.enyeinteractive.demo.marvelapi.viewmodel.MarvelViewModel

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel : MarvelViewModel by viewModels()

        setContent {
            MainScreen(viewModel)
        }
        //TODO: bind view model

        //TODO: run query

        //TODO: set up compose object

        //TODO: render ui as a compose lazy column


    }

}