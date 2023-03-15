package com.example.surfit.ui.addNewCar

import android.net.Uri

data class AddNewCarScreenState(
    val name: String = "",
    val year: String = "",
    val engineCapacity: String = "",
    val imageUri: Uri = Uri.EMPTY
)
