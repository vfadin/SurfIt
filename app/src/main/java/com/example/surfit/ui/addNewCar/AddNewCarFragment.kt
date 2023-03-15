package com.example.surfit.ui.addNewCar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.surfit.ui.theme.SurfItTheme
import com.example.surfit.utils.Constants.FREE_ADD_ATTEMPTS_COUNT
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
                        if (event is AddNewCarEvent.OnSaveClick) {
                            val prefs = SharedPreferences(requireContext())
                            if (prefs.restoreAddAttemptNumber() < FREE_ADD_ATTEMPTS_COUNT) {
                                prefs.saveAddAttemptNumber(prefs.restoreAddAttemptNumber() + 1)
                                viewModel.onEvent(event)
                            }
                        } else {
                            viewModel.onEvent(event)
                        }
                    }
                }
            }
        }
    }
}