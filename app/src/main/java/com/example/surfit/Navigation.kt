package com.example.surfit

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import java.security.InvalidParameterException

sealed class Screen {
    object Home : Screen()
    object AddNewCar : Screen()
    object Settings : Screen()
    data class FullCarInfo(val id: Int) : Screen()
}

fun Fragment.navigate(to: Screen, from: Screen) {
    if (to == from) {
        throw InvalidParameterException("Can't navigate to $to")
    }
    when (to) {
        Screen.Home -> {
            findNavController().popBackStack()
            findNavController().navigate(R.id.home_fragment)
        }
        Screen.AddNewCar -> findNavController().navigate(R.id.add_new_car_fragment)
        is Screen.FullCarInfo -> {
            findNavController().navigate(
                R.id.full_car_info_fragment,
                Bundle().apply { putInt("id", to.id) }
            )
        }
        Screen.Settings -> findNavController().navigate(R.id.settings_fragment)
    }
}
