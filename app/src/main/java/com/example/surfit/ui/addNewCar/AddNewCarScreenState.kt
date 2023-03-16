package com.example.surfit.ui.addNewCar

import android.net.Uri

data class AddNewCarScreenState(
    val name: String = "",
    val nameError: String = "",
    val year: String = "",
    val yearError: String = "",
    val engineCapacity: String = "",
    val engineCapacityError: String = "",
    val isValuesValid: Boolean = false,
    val imageUri: Uri = Uri.EMPTY
)
