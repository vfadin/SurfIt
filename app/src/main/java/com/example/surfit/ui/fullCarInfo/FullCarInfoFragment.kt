package com.example.surfit.ui.fullCarInfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.surfit.ui.theme.SurfItTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FullCarInfoFragment : Fragment() {

    private val viewModel: FullCarInfoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val currentCarIndex = arguments?.getInt("id") ?: 0
        viewModel.getCar(currentCarIndex)
        return ComposeView(requireContext()).apply {
            setContent {
                SurfItTheme {
                    FullCarInfoScreen(viewModel) {
                        findNavController().navigateUp()
                    }
                }
            }
        }
    }
}