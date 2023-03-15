package com.example.surfit.ui.home

sealed class HomeEvent {
    data class OnSearchTextChanged(val text: String) : HomeEvent()
    object OnItemClick : HomeEvent()
}
