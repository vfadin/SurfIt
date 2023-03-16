package com.example.surfit.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.surfit.domain.repo.IHomeRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val homeRepo: IHomeRepo,
) : ViewModel() {

    fun deleteIdsFromDatabase() {
        viewModelScope.launch(Dispatchers.IO) {
            homeRepo.clearIds()
        }
    }
}