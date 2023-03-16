package com.example.surfit.ui.home

sealed class HomeEvent {
    data class OnSearchTextChanged(val text: String) : HomeEvent()
    data class OnItemClick(val id: Int) : HomeEvent()
    object OnAddNewClick : HomeEvent()
    object OnDoPurchaseClick: HomeEvent()
    object OnDismissPurchaseClick: HomeEvent()
    object OnSettingsClick: HomeEvent()
}
