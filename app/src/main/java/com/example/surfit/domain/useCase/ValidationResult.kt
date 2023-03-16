package com.irpsu.events.domain.usecase

data class ValidationResult(
    val successful : Boolean,
    val errorMessage : String = ""
)