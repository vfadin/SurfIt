package com.example.surfit.domain.useCase

import com.irpsu.events.domain.usecase.ValidationResult

class ValidateYear {
    fun invoke(year: String): ValidationResult {
        try {
            if (year.isEmpty()) {
                return ValidationResult(false, "Поле не может быть пустым")
            }
            year.toInt()
        } catch (e: Exception) {
            return ValidationResult(false, "Поле может содержать только цифры")
        }
        return ValidationResult(true)
    }
}