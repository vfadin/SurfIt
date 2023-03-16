package com.example.surfit.domain.useCase

import com.irpsu.events.domain.usecase.ValidationResult

class ValidateEngineCapacity {

    operator fun invoke(engineCapacity: String): ValidationResult {
        engineCapacity.forEach {
            if (!it.isDigit() && it != '.') {
                return ValidationResult(
                    false,
                    "Объём двигателя может быть задан в формате X.X"
                )
            }
        }
        return ValidationResult(true)
    }
}