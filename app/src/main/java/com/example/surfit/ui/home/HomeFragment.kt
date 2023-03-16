package com.example.surfit.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.surfit.Screen
import com.example.surfit.navigate
import com.example.surfit.ui.theme.SurfItTheme
import com.example.surfit.utils.Constants.FREE_ADD_ATTEMPTS_COUNT
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
        viewModel.refresh()
        return ComposeView(requireContext()).apply {
            setContent {
                SurfItTheme {
                    HomeScreen(viewModel) { event ->
                        when (event) {
                            is HomeEvent.OnItemClick -> {
                                navigate(Screen.FullCarInfo(event.id), Screen.Home)
                            }
                            is HomeEvent.OnSearchTextChanged -> {
                                viewModel.onSearchTextChanged(event.text)
                            }
                            HomeEvent.OnAddNewClick -> {
                                val prefs = SharedPreferences(context)
                                val attempt = prefs.restoreAddAttemptNumber()
                                val token = prefs.restorePurchaseToken()
                                if (attempt < FREE_ADD_ATTEMPTS_COUNT || token == "PAID_TOKEN") {
                                    navigate(Screen.AddNewCar, Screen.Home)
                                } else {
                                    viewModel.showPurchase()
                                }
                            }
                            HomeEvent.OnDoPurchaseClick -> doPurchase()
                            HomeEvent.OnDismissPurchaseClick -> viewModel.dismissPurchase()
                            HomeEvent.OnSettingsClick -> navigate(Screen.Settings, Screen.Home)
                        }
                    }
                }
            }
        }
    }

    private fun doPurchase() {
        SharedPreferences(requireContext()).savePurchaseToken("PAID_TOKEN")
    }
}