package com.example.surfit.ui.home

sealed class HomeEvent {
    data class OnSearchTextChanged(val text: String) : HomeEvent()
    object OnAddNewClick : HomeEvent()
    data class OnItemClick(val id: Int) : HomeEvent()
}
