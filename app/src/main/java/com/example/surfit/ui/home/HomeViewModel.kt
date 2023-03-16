package com.example.surfit.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.surfit.domain.entity.Car
import com.example.surfit.domain.repo.IHomeRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepo: IHomeRepo,
) : ViewModel() {
    private var carsList = listOf<Car>()
    private val _carsStateFlow = MutableStateFlow<List<Car>?>(null)
    val carsStateFlow = _carsStateFlow.asStateFlow().filterNotNull()
    val sortList = listOf(Sorts.Default, Sorts.Name, Sorts.EngineCapacity, Sorts.Year)
    var chosenSortIndex = 0
        private set
    var searchState by mutableStateOf("")
        private set
    var purchaseDialogVisible by mutableStateOf(false)
        private set

    fun refresh() {
        viewModelScope.launch(Dispatchers.IO) {
            carsList = homeRepo.getCars()
            _carsStateFlow.value = carsList
        }
    }

    fun onSearchTextChanged(text: String) {
        searchState = text
        _carsStateFlow.value = carsList.filter {
            it.name.contains(text, true)
        }
    }

    fun onSortChosen(sortId: Int) {
        chosenSortIndex = sortId
        _carsStateFlow.value = when (sortList[sortId]) {
            Sorts.Name -> _carsStateFlow.value?.sortedBy { it.name }
            Sorts.Year -> _carsStateFlow.value?.sortedBy { it.year }
            Sorts.EngineCapacity -> _carsStateFlow.value?.sortedBy { it.engineCapacity }
            Sorts.Default -> carsList
        }
    }

    fun showPurchase() {
        purchaseDialogVisible = true
    }

    fun dismissPurchase() {
        purchaseDialogVisible = false
    }
}

sealed class Sorts {
    object Default : Sorts()
    object Name : Sorts()
    object Year : Sorts()
    object EngineCapacity : Sorts()
}