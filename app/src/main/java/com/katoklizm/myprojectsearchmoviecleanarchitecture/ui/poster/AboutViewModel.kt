package com.katoklizm.myprojectsearchmoviecleanarchitecture.ui.poster

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.api.MoviesInteractor
import com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.models.MovieDetails
import com.katoklizm.myprojectsearchmoviecleanarchitecture.presentation.poster.AboutState
import kotlinx.coroutines.launch

class AboutViewModel(
    private val movieId: String,
    private val moviesInteractor: MoviesInteractor
) : ViewModel() {

    private val stateLiveData = MutableLiveData<AboutState>()
    fun observeState(): LiveData<AboutState> = stateLiveData

    init {
        viewModelScope.launch {
            try {
                moviesInteractor.getMoviesDetails(movieId)
                    .collect { (movieDetails, errorMessage) ->
                        if (movieDetails != null) {
                            stateLiveData.postValue(AboutState.Content(movieDetails))
                        } else {
                            stateLiveData.postValue(AboutState.Error(errorMessage ?: "Ошибка сервера"))
                        }
                    }
            } catch (e: Exception) {
                stateLiveData.postValue(AboutState.Error("Ошибка сервера"))
            }
        }
//        moviesInteractor.getMoviesDetails(movieId, object : MoviesInteractor.MovieDetailsConsumer {
//            override fun consume(movieDetails: MovieDetails?, errorMessage: String?) {
//                if (movieDetails != null) {
//                    stateLiveData.postValue(AboutState.Content(movieDetails))
//                } else {
//                    stateLiveData.postValue(AboutState.Error(errorMessage ?: "Ошибка сервера"))
//                }
//            }
//
//        })
    }
}