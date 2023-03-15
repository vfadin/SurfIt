package com.example.surfit.ui.fullCarInfo

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
class FullCarInfoViewModel @Inject constructor(
    private val homeRepo: IHomeRepo,
) : ViewModel() {
    private val _carStateFlow = MutableStateFlow<Car?>(null)
    val carStateFlow = _carStateFlow.asStateFlow().filterNotNull()

    fun getCar(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _carStateFlow.value = homeRepo.getCar(id)
        }
    }
}