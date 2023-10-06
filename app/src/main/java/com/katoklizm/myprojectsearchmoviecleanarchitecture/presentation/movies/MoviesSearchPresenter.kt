package com.katoklizm.myprojectsearchmoviecleanarchitecture.presentation.movies

import android.content.Context
import android.os.Looper
import android.os.Handler
import com.katoklizm.myprojectsearchmoviecleanarchitecture.R
import com.katoklizm.myprojectsearchmoviecleanarchitecture.util.Creator
import com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.api.MoviesInteractor
import com.katoklizm.myprojectsearchmoviecleanarchitecture.ui.movies.MoviesAdapter
import com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.models.Movie
import com.katoklizm.myprojectsearchmoviecleanarchitecture.ui.movies.models.MoviesState

class MoviesSearchPresenter(
    private val context: Context
) {

    private var view: MoviesView? = null
    private var state: MoviesState? = null
    private var latestSearchText: String? = null

    private val moviesInteractor = Creator.provideMoviesInteractor(context)

    companion object {
        private const val SEARCH_DEBOUNCE_DELAY = 2000L
    }

    private var lastSearchText: String? = null

    private val handler = Handler(Looper.getMainLooper())

    private val searchRunnable = Runnable {
        val newSearchText = lastSearchText ?: ""
        searchRequest(newSearchText)
    }

    fun attachView(view: MoviesView) {
        this.view = view
        state?.let { view.render(it) }
    }

    fun detachView() {
        this.view = null
    }

    fun onDestroy() {
        handler.removeCallbacks(searchRunnable)
    }

    fun searchDebounce(changedText: String) {
        this.lastSearchText = changedText
        handler.removeCallbacks(searchRunnable)
        handler.postDelayed(searchRunnable, SEARCH_DEBOUNCE_DELAY)
    }

    private fun searchRequest(newSearchText: String) {
        if (latestSearchText == newSearchText) {
            return
        }

        this.latestSearchText = newSearchText

        if (newSearchText.isNotEmpty()) {
            view?.render(
                MoviesState.Loading
            )

            moviesInteractor.searchMovies(
                newSearchText,
                object : MoviesInteractor.MoviesConsumer {
                    override fun consume(foundMovies: List<Movie>?, errorMessage: String?) {
                        handler.post {
                            val movies = mutableListOf<Movie>()
                            if (foundMovies != null) {
//                                movies.clear()
                                movies.addAll(foundMovies)
                            }

                            when {
                                errorMessage != null -> {
                                    view?.render(
                                        MoviesState.Error(
                                            errorMessage = context.getString(R.string.something_went_wrong),
                                        )
                                    )
                                    view?.showToast(errorMessage)
                                }

                                movies.isEmpty() -> {
                                    view?.render(
                                        MoviesState.Empty(
                                            message = context.getString(R.string.nothing_found),
                                        )
                                    )
                                }

                                else -> {
                                    view?.render(
                                        MoviesState.Content(
                                            movies = movies
                                        )
                                    )
                                }
                            }
                        }
                    }
                })
        }
    }
}