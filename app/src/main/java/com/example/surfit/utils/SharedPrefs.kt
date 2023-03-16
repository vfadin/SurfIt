package com.example.surfit.utils

import android.content.Context

class SharedPreferences(private val context: Context) {

    fun saveViewAttemptNumber(number: Int) {
        context.getSharedPreferences("number", Context.MODE_PRIVATE)
            .edit().putInt("number", number).apply()
    }

    fun restoreViewAttemptNumber(): Int {
        return context.getSharedPreferences("number", Context.MODE_PRIVATE)
            .getInt("number", 0)
    }

    fun saveAddAttemptNumber(number: Int) {
        context.getSharedPreferences("number_add", Context.MODE_PRIVATE)
            .edit().putInt("number_add", number).apply()
    }

    fun restoreAddAttemptNumber(): Int {
        return context.getSharedPreferences("number_add", Context.MODE_PRIVATE)
            .getInt("number_add", 0)
    }

    fun savePurchaseToken(token: String) {
        context.getSharedPreferences("token", Context.MODE_PRIVATE)
            .edit().putString("token", token).apply()
    }

    fun restorePurchaseToken(): String {
        return context.getSharedPreferences("token", Context.MODE_PRIVATE)
            .getString("token", "") ?: ""
    }
}