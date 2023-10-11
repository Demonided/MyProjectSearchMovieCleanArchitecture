package com.katoklizm.myprojectsearchmoviecleanarchitecture.presentation.movies

sealed interface ToastState {
    object None: ToastState
    data class Show(val additionalMessage: String): ToastState
}