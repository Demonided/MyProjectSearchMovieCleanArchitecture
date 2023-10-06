package com.katoklizm.myprojectsearchmoviecleanarchitecture.presentation

class PosterPresenter(
    private val view: PosterView,
    private val imageUrl: String,
) {

    fun onCreate() {
        view.setupPosterImage(imageUrl)
    }
}