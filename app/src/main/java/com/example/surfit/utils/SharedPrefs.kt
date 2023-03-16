package com.example.surfit.utils

import android.content.Context

class SharedPreferences(private val context: Context) {

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

    fun clear() {
        context.getSharedPreferences("number_add", Context.MODE_PRIVATE).edit().clear().apply()
        context.getSharedPreferences("token", Context.MODE_PRIVATE).edit().clear().apply()
    }
}