package com.example.surfit.ui.settings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.surfit.R
import com.example.surfit.ui.components.CustomTopBar

@Composable
fun SettingsScreen(onRestoreClick: () -> Unit) {
    Scaffold(
        topBar = {
            CustomTopBar(title = stringResource(R.string.title_settings), isSettingsIconVisible = false)
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { onRestoreClick() }
            ) {
                Text(text = stringResource(R.string.label_restore_init_state))
            }
        }
    }
}