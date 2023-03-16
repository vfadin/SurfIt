package com.example.surfit.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.surfit.ui.theme.SurfItTheme
import com.example.surfit.utils.SharedPreferences
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : Fragment() {

    private val viewModel: SettingsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                SurfItTheme {
                    SettingsScreen(
                        onBackIconClick = { findNavController().navigateUp() },
                        onRestoreClick = {
                            SharedPreferences(requireContext()).clear()
                            viewModel.deleteIdsFromDatabase()
                        }
                    )
                }
            }
        }
    }
}