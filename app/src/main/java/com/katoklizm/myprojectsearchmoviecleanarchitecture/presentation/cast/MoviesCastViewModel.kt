package com.katoklizm.myprojectsearchmoviecleanarchitecture.presentation.cast

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.api.MoviesInteractor
import com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.models.MovieCast
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlin.math.log

// В конструктор пробросили необходимые для запроса параметры
class MoviesCastViewModel(
    private val movieId: String,
    private val moviesInteractor: MoviesInteractor
) : ViewModel() {

    private var searchJob: Job? = null

    // Стандартная обвязка для определения State
    // и наблюдения за ним в UI-слое
    private val stateLiveData = MutableLiveData<MoviesCastState>()
    fun observeState(): LiveData<MoviesCastState> = stateLiveData

    init {

        viewModelScope.launch {
            // При старте экрана покажем ProgressBar
            stateLiveData.postValue(MoviesCastState.Loading)

            try {
                moviesInteractor.getMovieCast(movieId = movieId).collect {(movieCast, errorMessage) ->
                    if (movieCast != null) {
                        stateLiveData.postValue(castToUiStateContent(movieCast))
                    } else {
                        stateLiveData.postValue(MoviesCastState.Error(errorMessage ?: "Unknown error"))
                    }
                }
            } catch (e: Throwable) {
                Log.d("ErrorScope", "Тут я ловлю ошибку ${moviesInteractor.getMovieCast(movieId)}")
                stateLiveData.postValue(MoviesCastState.Error(""))
            }
        }
        // Выполняем сетевой запрос
//        moviesInteractor.getMovieCast(movieId, object : MoviesInteractor.MovieCastConsumer {
//
//            // Обрабатываем результат этого запроса
//            override fun consume(movieCast: MovieCast?, errorMessage: String?) {
//
//            }
//
//        })
    }

    private fun castToUiStateContent(cast: MovieCast): MoviesCastState {
        val items = buildList<MoviesCastRVItem> {
            // Если есть хотя бы один режиссёр, добавим заголовок
            if (cast.directors.isNotEmpty()) {
                this += MoviesCastRVItem.HeaderItem("Directors")
                this += cast.directors.map { MoviesCastRVItem.PersonItem(it) }
            }

            // Если есть хотя бы один сценарист, добавим заголовок
            if (cast.writers.isNotEmpty()) {
                this += MoviesCastRVItem.HeaderItem("Writers")
                this += cast.writers.map { MoviesCastRVItem.PersonItem(it) }
            }

            //
            if (cast.actors.isNotEmpty()) {
                this += MoviesCastRVItem.HeaderItem("Actors")
                this += cast.actors.map { MoviesCastRVItem.PersonItem(it) }
            }

            //
            if (cast.others.isNotEmpty()) {
                this += MoviesCastRVItem.HeaderItem("Others")
                this += cast.others.map { MoviesCastRVItem.PersonItem(it) }
            }
        }

        return MoviesCastState.Content(
            fullTitle = cast.fullTitle,
            items = items
        )
    }
}