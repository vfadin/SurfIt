package com.example.surfit.ui.fullCarInfo

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.Placeholder
import com.bumptech.glide.integration.compose.placeholder
import com.example.surfit.R
import com.example.surfit.ui.components.CustomTopBar
import com.example.surfit.ui.theme.PlaceholderGray

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun FullCarInfoScreen(viewModel: FullCarInfoViewModel, onBackClick: () -> Unit) {
    val car by viewModel.carStateFlow.collectAsState(null)
    Scaffold(
        topBar = {
            CustomTopBar(title = "", isBackIconVisible = true, onBackIconClick = onBackClick)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            car?.apply {
                Text(
                    text = "Автомобиль был добавлен в коллекцию $createdAt",
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.body1,
                    color = PlaceholderGray
                )
                Text(
                    text = "$name, $year года выпуска",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    style = MaterialTheme.typography.h2
                )
                GlideImage(
                    model = photo,
                    contentDescription = null,
                    failure = placeholder(R.drawable.image_placeholder)
                )
            }
        }
    }
}