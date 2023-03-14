package com.example.surfit

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import java.security.InvalidParameterException

enum class Screen { Home }

fun Fragment.navigate(to: Screen, from: Screen) {
    if (to == from) {
        throw InvalidParameterException("Can't navigate to $to")
    }
    when (to) {
        Screen.Home -> {
            findNavController().popBackStack()
            findNavController().navigate(R.id.home_fragment)
        }
    }
}