package com.katoklizm.myprojectsearchmoviecleanarchitecture.presentation.names

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.katoklizm.myprojectsearchmoviecleanarchitecture.R
import com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.api.NamesInteractor
import com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.models.Person
import com.katoklizm.myprojectsearchmoviecleanarchitecture.presentation.SingleLiveEvent
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class NamesViewModel(
    private val context: Context,
    private val namesInteractor: NamesInteractor
) : ViewModel() {

    private val handler = Handler(Looper.getMainLooper())

    private val stateLiveData = MutableLiveData<NamesState>()
    fun observeState(): LiveData<NamesState> = stateLiveData

    private val showToast = SingleLiveEvent<String?>()
    fun observeShowToast(): LiveData<String?> = showToast

    private var latestSearchText: String? = null

    private var searchJob: Job? = null

//    override fun onCleared() {
//        handler.removeCallbacksAndMessages(SEARCH_REQUEST_TOKEN)
//    }

    fun searchDebounce(changetText: String) {
        if (latestSearchText == changetText) {
            return
        }

        latestSearchText = changetText

        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(SEARCH_DEBOUNCE_DELAY)
            searchRequest(changetText)
        }

//        this.latestSearchText = changetText
//        handler.removeCallbacksAndMessages(SEARCH_REQUEST_TOKEN)
//
//        val searchRunable = Runnable { searchRequest(changetText) }
//
//        val postTime = SystemClock.uptimeMillis() + SEARCH_DEBOUNCE_DELAY
//        handler.postAtTime(
//            searchRunable,
//            SEARCH_REQUEST_TOKEN,
//            postTime
//        )
    }

    private fun searchRequest(newSearchText: String) {
        if (newSearchText.isNotEmpty()) {
            renderState(NamesState.Loading)

            viewModelScope.launch {
                namesInteractor.searchNames(newSearchText)
                    .collect { pair ->
                        processResult(pair.first, pair.second)
                    }
            }
//            namesInteractor.searchNames(newSearchText, object : NamesInteractor.NamesConsumer {
//                override fun consume(foundNames: List<Person>?, errorMessage: String?) {
//                    val person = mutableListOf<Person>()
//                    if (foundNames != null) {
//                        person.addAll(foundNames)
//                    }
//
//                    when {
//                        errorMessage != null -> {
//                            renderState(
//                                NamesState.Error(
//                                    message = context.getString(
//                                        R.string.something_went_wrong
//                                    )
//                                )
//
//                            )
//                            showToast.postValue(errorMessage)
//                        }
//
//                        person.isEmpty() -> {
//                            renderState(
//                                NamesState.Empty(
//                                    message = context.getString(R.string.nothing_found)
//                                )
//                            )
//                        }
//
//                        else -> {
//                            renderState(
//                                NamesState.Content(
//                                    persons = person
//                                )
//                            )
//                        }
//                    }
//                }
//            })
        }
    }

    private fun processResult(foundNames: List<Person>?, errorMessage: String?) {
        val person = mutableListOf<Person>()

        if (foundNames != null) {
            person.addAll(foundNames)
        }

        when {
            errorMessage != null -> {
                renderState(NamesState.Error(message = context.getString(
                    R.string.something_went_wrong
                )))
                showToast.postValue(errorMessage)
            }
            person.isEmpty() -> {
                renderState(NamesState.Empty(message = context.getString(
                    R.string.nothing_found
                )))
            }
            else -> {
                renderState(NamesState.Content(persons = person))
            }
        }
    }

    private fun renderState(state: NamesState) {
        stateLiveData.postValue(state)
    }

    companion object {
        private const val SEARCH_DEBOUNCE_DELAY = 2000L
        private val SEARCH_REQUEST_TOKEN = Any()
    }

}