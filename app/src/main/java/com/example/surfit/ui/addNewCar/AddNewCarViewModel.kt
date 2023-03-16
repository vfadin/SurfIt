package com.example.surfit.ui.addNewCar

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.surfit.domain.entity.Car
import com.example.surfit.domain.repo.IHomeRepo
import com.example.surfit.domain.useCase.ValidateCarName
import com.example.surfit.domain.useCase.ValidateEngineCapacity
import com.example.surfit.domain.useCase.ValidateYear
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
        private set

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
            if (checkValues()) {
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

    private fun checkValues(): Boolean {
        with(screenState) {
            val validateName = ValidateCarName().invoke(name)
            val validateYear = ValidateYear().invoke(year)
            val validateEngineCapacity = ValidateEngineCapacity().invoke(engineCapacity)
            val isSuccessful = listOf(
                validateName, validateYear, validateEngineCapacity
            ).all { it.successful }

            if (!isSuccessful) {
                screenState = copy(
                    nameError = validateName.errorMessage,
                    yearError = validateYear.errorMessage,
                    engineCapacityError = validateEngineCapacity.errorMessage,
                )
                return false
            }
            screenState = copy(isValuesValid = true)
            return true
        }
    }
}