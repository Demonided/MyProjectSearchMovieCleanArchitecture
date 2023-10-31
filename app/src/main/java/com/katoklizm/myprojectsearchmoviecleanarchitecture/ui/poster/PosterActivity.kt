package com.katoklizm.myprojectsearchmoviecleanarchitecture.ui.poster

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.katoklizm.myprojectsearchmoviecleanarchitecture.R
import com.katoklizm.myprojectsearchmoviecleanarchitecture.presentation.PosterPresenter
import com.katoklizm.myprojectsearchmoviecleanarchitecture.presentation.PosterView
import com.katoklizm.myprojectsearchmoviecleanarchitecture.presentation.PosterViewModel
import com.katoklizm.myprojectsearchmoviecleanarchitecture.presentation.movies.MoviesSearchViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

//import com.katoklizm.myprojectsearchmoviecleanarchitecture.util.Creator

class PosterActivity : AppCompatActivity(), PosterView {
    private val viewModel by viewModel<PosterViewModel>()

    private lateinit var posterPresenter: PosterPresenter

    private lateinit var poster: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Мы не можем создать PosterPresenter раньше,
        // потому что нам нужен imageUrl, который
        // станет доступен только после super.onCreate
        val imageUrl = intent.extras?.getString("poster", "") ?: ""
//        posterPresenter = Creator.providePosterPresenter(this, imageUrl)

        setContentView(R.layout.activity_poster)
        poster = findViewById(R.id.poster)

        viewModel.onCreate()
    }

    override fun setupPosterImage(url: String) {
        Glide.with(applicationContext)
            .load(url)
            .into(poster)
    }
}