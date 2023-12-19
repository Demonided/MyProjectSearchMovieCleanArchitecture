package com.katoklizm.myprojectsearchmoviecleanarchitecture.presentation.movies

import android.os.Looper
import android.os.Handler
import android.os.SystemClock
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
//import com.katoklizm.myprojectsearchmoviecleanarchitecture.util.Creator
import com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.api.MoviesInteractor
import com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.models.Movie
import com.katoklizm.myprojectsearchmoviecleanarchitecture.presentation.SingleLiveEvent
import com.katoklizm.myprojectsearchmoviecleanarchitecture.ui.movies.models.MoviesState
import com.katoklizm.myprojectsearchmoviecleanarchitecture.util.debounce
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MoviesSearchViewModel(
    private val moviesInteractor: MoviesInteractor
) : ViewModel() {

    private val stateLiveData = MutableLiveData<MoviesState>()
    val observeState: LiveData<MoviesState> = stateLiveData

    private val toastState = MutableLiveData<ToastState>(ToastState.None)
    val observeToastState: LiveData<ToastState> = toastState

    private val showToast = SingleLiveEvent<String?>()

    private var view: MoviesView? = null
    private var state: MoviesState? = null
    private var latestSearchText: String? = null

//    private val moviesInteractor = Creator.provideMoviesInteractor(getApplication<Application>())

    private val mediatorStateLiveData = MediatorLiveData<MoviesState>().also { liveData ->
        // 1
        liveData.addSource(stateLiveData) { movieState ->
            liveData.value = when (movieState) {
                // 2
                is MoviesState.Content -> MoviesState.Content(movieState.movies.sortedByDescending { it.inFavorite })
                is MoviesState.Empty -> movieState
                is MoviesState.Error -> movieState
                is MoviesState.Loading -> movieState
            }
        }
    }

    fun observeState(): LiveData<MoviesState> = mediatorStateLiveData

    private var lastSearchText: String? = null

    private val handler = Handler(Looper.getMainLooper())

    fun attachView(view: MoviesView) {
        this.view = view
        state?.let { view.render(it) }
    }

//    override fun onCleared() {
//        handler.removeCallbacksAndMessages(SEARCH_REQUEST_TOKEN)
//    }

//    fun toggleFavorite(movie: Movie) {
//        if (movie.inFavorite) {
//            moviesInteractor.removeMovieFromFavorites(movie = movie)
//        } else {
//            moviesInteractor.addMovieToFavorites(movie = movie)
//        }
//
//        updateMoviesContent(movie.id, movie.copy(inFavorite = !movie.inFavorite))
//    }
//
//    private val movieSearchDebounce = debounce<String>(SEARCH_DEBOUNCE_DELAY, viewModelScope, true) { changedText ->
//        searchRequest(changedText)
//    }

    private var searchJob: Job? = null

    private fun updateMoviesContent(movieId: String, newMovie: Movie) {
        val currentState = stateLiveData.value

        if (currentState is MoviesState.Content) {

            val movieIndex = currentState.movies.indexOfFirst { it.id == movieId }

            if (movieIndex != -1) {
                stateLiveData.value = MoviesState.Content(
                    currentState.movies.toMutableList().also {
                        it[movieIndex] = newMovie
                    }
                )
            }
        }
    }

    fun searchDebounce(changedText: String) {
        if (latestSearchText == changedText) {
//            latestSearchText = changedText
//            movieSearchDebounce(changedText)
            return
        }
//        if (latestSearchText == changedText) {
//            return
//        }

        latestSearchText = changedText

        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(SEARCH_DEBOUNCE_DELAY)
            searchRequest(changedText)
        }

//        this.lastSearchText = changedText
//        handler.removeCallbacksAndMessages(SEARCH_REQUEST_TOKEN)
//
//        val searchRunnable = Runnable { searchRequest(changedText) }
//
//        val postTime = SystemClock.uptimeMillis() + SEARCH_DEBOUNCE_DELAY
//        handler.postAtTime(
//            searchRunnable,
//            SEARCH_REQUEST_TOKEN,
//            postTime
//        )
    }

    private fun searchRequest(newSearchText: String) {
        if (newSearchText.isNotEmpty()) {
            renderState(
                MoviesState.Loading
            )

            viewModelScope.launch {
                moviesInteractor.searchMovies(newSearchText)
                    .collect { pair ->
                        processResult(pair.first, pair.second)
                    }
            }

//            moviesInteractor.searchMovies(
//                newSearchText,
//                object : MoviesInteractor.MoviesConsumer {
//                    override fun consume(foundMovies: List<Movie>?, errorMessage: String?) {
//
//                        val movies = mutableListOf<Movie>()
//                        if (foundMovies != null) {
//                            movies.addAll(foundMovies)
//                        }
//
//                        when {
//                            errorMessage != null -> {
//                                renderState(
//                                    MoviesState.Error(
//                                        errorMessage = "dddd"
//                                    ),
//                                )
//
//                                view?.showToast(errorMessage)
//                            }
//
//                            movies.isEmpty() -> {
//                                renderState(
//                                    MoviesState.Empty(
//                                        message = "ssss"
//                                    ),
//                                )
//
//                            }
//
//                            else -> {
//                                renderState(
//                                    MoviesState.Content(
//                                        movies = movies
//                                    )
//                                )
//                            }
//                        }
//                    }
//                })
        }
    }

    private fun processResult(foundMovies: List<Movie>?, errorMessage: String?) {
        val movie = mutableListOf<Movie>()

        if (foundMovies != null) {
            movie.addAll(foundMovies)
        }

        when {
            errorMessage != null -> {
                renderState(MoviesState.Error("gggg"))
                showToast.postValue(errorMessage)
            }
            movie.isEmpty() -> {
                renderState(MoviesState.Empty("Empty"))
            }
            else -> {
                renderState(MoviesState.Content(movies = movie))
            }
        }
    }

    private fun renderState(state: MoviesState) {
        stateLiveData.postValue(state)
    }

    fun toastWasShown() {
        toastState.value = ToastState.None
    }

    fun observeShowToast(): LiveData<String?> = showToast

    companion object {
        private const val SEARCH_DEBOUNCE_DELAY = 2000L
        private val SEARCH_REQUEST_TOKEN = Any()

//        fun getViewModelFactory(): ViewModelProvider.Factory = viewModelFactory {
//            initializer {
//                MoviesSearchViewModel(this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as Application)
//            }
//        }
    }
}