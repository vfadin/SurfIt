package com.example.surfit.ui.fullCarInfo

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.example.surfit.R
import com.example.surfit.ui.components.CustomTopBar
import com.example.surfit.ui.components.PurchaseDialog
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
        if (car == null) {
            PurchaseDialog(
                title = "Вы уже просмотрели три автомобиля\n\n. У вас закончились попытки бесплатного просмотра, но вы всё ещё можете просматривать автомобили, просмотренные ранее. Оплатите платную версию приложения для снятия всех ограничений.",
                onBackClick = onBackClick,
                onDismissClick = {},
                onPurchaseClick = {}
            )
        }
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
                    model = photoUri,
                    contentDescription = null,
                    failure = placeholder(R.drawable.image_placeholder)
                )
            }
        }
    }
}