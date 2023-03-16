package com.example.surfit.ui.addNewCar

import android.net.Uri

sealed class AddNewCarEvent {
    data class OnNameChanged(val text: String) : AddNewCarEvent()
    data class OnYearChanged(val text: String) : AddNewCarEvent()
    data class OnEngineCapacityChanged(val text: String) : AddNewCarEvent()
    data class OnImageChosen(val uri: Uri) : AddNewCarEvent()
    object OnSaveClick : AddNewCarEvent()
    object OnDoPurchaseClick : AddNewCarEvent()
}
