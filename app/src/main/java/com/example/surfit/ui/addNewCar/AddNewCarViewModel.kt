package com.example.surfit.ui.addNewCar

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.surfit.domain.entity.Car
import com.example.surfit.domain.repo.IHomeRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class AddNewCarViewModel @Inject constructor(
    private val homeRepo: IHomeRepo,
) : ViewModel() {
    var screenState by mutableStateOf(AddNewCarScreenState())

    fun onEvent(event: AddNewCarEvent) {
        when (event) {
            is AddNewCarEvent.OnEngineCapacityChanged -> {
                screenState = screenState.copy(engineCapacity = event.text)
            }
            is AddNewCarEvent.OnNameChanged -> {
                screenState = screenState.copy(name = event.text)
            }
            is AddNewCarEvent.OnYearChanged -> {
                screenState = screenState.copy(year = event.text)
            }
            is AddNewCarEvent.OnImageChosen -> {
                screenState = screenState.copy(imageUri = event.uri)
            }
            AddNewCarEvent.OnSaveClick -> {
                onSaveClick()
            }
            else -> {}
        }
    }

    private fun onSaveClick() {
        viewModelScope.launch(Dispatchers.IO) {
            screenState.apply {
                homeRepo.insertCar(
                    Car(
                        name = name,
                        photo = imageUri,
                        year = year.toIntOrNull() ?: 0,
                        engineCapacity = engineCapacity.toDoubleOrNull() ?: .0,
                        createdAt = SimpleDateFormat(
                            "dd.MM.yyyy",
                            Locale.getDefault()
                        ).format(Calendar.getInstance().time)
                    )
                )
            }
        }
    }
}