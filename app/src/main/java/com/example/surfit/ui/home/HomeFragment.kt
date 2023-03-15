package com.example.surfit.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.surfit.Screen
import com.example.surfit.navigate
import com.example.surfit.utils.Constants.FREE_VIEW_ATTEMPTS_COUNT
import com.example.surfit.utils.SharedPreferences
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                HomeScreen(viewModel) { event ->
                    when (event) {
                        HomeEvent.OnAddNewClick -> navigate(Screen.AddNewCar, Screen.Home)
                        HomeEvent.OnItemClick -> {
                            val prefs = SharedPreferences(requireContext())
                            val prevAttemptNumber = prefs.restoreViewAttemptNumber()
                            if (prevAttemptNumber < FREE_VIEW_ATTEMPTS_COUNT) {
                                prefs.saveViewAttemptNumber(prevAttemptNumber + 1)
                                // TODO: add navigate
                            }
                        }
                        is HomeEvent.OnSearchTextChanged -> viewModel.onSearchTextChanged(event.text)
                    }
                }
            }
        }
    }

    private fun doPurchase() {
        SharedPreferences(requireContext()).savePurchaseToken("PAID_TOKEN")
    }
}