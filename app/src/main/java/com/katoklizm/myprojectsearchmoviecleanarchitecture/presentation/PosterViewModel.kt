package com.katoklizm.myprojectsearchmoviecleanarchitecture.presentation

import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide

class PosterViewModel(
    private val view: PosterView,
    private val imageUrl: String,
): ViewModel() {


    fun onCreate() {
        view.setupPosterImage(imageUrl)
    }
}