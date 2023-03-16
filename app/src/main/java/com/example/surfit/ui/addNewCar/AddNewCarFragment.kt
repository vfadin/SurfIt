package com.example.surfit.ui.addNewCar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.surfit.ui.theme.SurfItTheme
import com.example.surfit.utils.SharedPreferences
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddNewCarFragment : Fragment() {
    private val viewModel: AddNewCarViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                SurfItTheme {
                    AddNewCarScreen(viewModel) { event ->
                        when (event) {
                            is AddNewCarEvent.OnSaveClick -> {
                                viewModel.onEvent(event)
                                findNavController().navigateUp()
                            }
                            is AddNewCarEvent.OnDoPurchaseClick -> {
                                doPurchase()
                            }
                            else -> {
                                viewModel.onEvent(event)
                            }
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