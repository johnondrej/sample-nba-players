package com.github.johnondrej.nbaplayers.shared.presentation

sealed class UiState<out T> {

    object Empty : UiState<Nothing>()
    object Loading : UiState<Nothing>()
    data class Loaded<out T>(val data: T) : UiState<T>()
    data class Error(val throwable: Throwable) : UiState<Nothing>()
}
