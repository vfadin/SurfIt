package com.example.surfit.domain.useCase

import com.irpsu.events.domain.usecase.ValidationResult

class ValidateCarName {

    fun invoke(name: String): ValidationResult {
        if (name.isEmpty()) {
            return ValidationResult(false, "Поле не может быть пустым")
        }
        name.forEach {
            if (!it.isLetter()) {
                return ValidationResult(false, "Поле содержать только буквы")
            }
        }
        return ValidationResult(true, "")
    }
}