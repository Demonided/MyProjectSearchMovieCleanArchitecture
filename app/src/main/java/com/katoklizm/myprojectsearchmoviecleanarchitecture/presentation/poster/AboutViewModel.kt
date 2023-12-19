package com.katoklizm.myprojectsearchmoviecleanarchitecture.presentation.poster

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.api.MoviesInteractor
import com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.models.MovieDetails
import com.katoklizm.myprojectsearchmoviecleanarchitecture.presentation.poster.AboutState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AboutViewModel(
    private val movieId: String,
    private val moviesInteractor: MoviesInteractor
) : ViewModel() {

    private val stateLiveData = MutableLiveData<AboutState>()
    fun observeState(): LiveData<AboutState> = stateLiveData

    init {

        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    moviesInteractor.getMoviesDetails(movieId)
                }.collect { (movieDetails, errorMessage) ->
                    withContext(Dispatchers.Main) {
                        if (movieDetails != null) {
                            stateLiveData.postValue(AboutState.Content(movieDetails))
                        } else {
                            stateLiveData.postValue(
                                AboutState.Error(
                                    errorMessage ?: "Ошибка сервера"
                                )
                            )
                        }
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    stateLiveData.postValue(AboutState.Error("Ошибка сервера"))
                }
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