package com.example.surfit.ui.home

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
    private val _carsStateFlow = MutableStateFlow<List<Car>?>(null)
    val carsStateFlow = _carsStateFlow.asStateFlow().filterNotNull()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _carsStateFlow.value = homeRepo.getCars()
        }
    }
}