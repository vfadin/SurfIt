package com.example.surfit.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.example.surfit.R
import com.example.surfit.domain.entity.Car
import com.example.surfit.ui.components.CustomTopBar
import com.example.surfit.ui.components.PurchaseDialog
import com.example.surfit.ui.components.SearchTextField

@Composable
fun HomeScreen(viewModel: HomeViewModel, event: (HomeEvent) -> Unit) {
    val cars by viewModel.carsStateFlow.collectAsState(emptyList())
    rememberCoroutineScope()
    var isPurchaseDialogVisible = viewModel.purchaseDialogVisible
    if (isPurchaseDialogVisible) {
        PurchaseDialog(
            title = "Вы уже добавили два автомобиля\n\n. У вас закончились попытки бесплатного добавления, но вы всё ещё можете просматривать автомобили, просмотренные ранее. Оплатите платную версию приложения для снятия всех ограничений.",
            onBackClick = { event(HomeEvent.OnDismissPurchaseClick) },
            onDismissClick = { event(HomeEvent.OnDismissPurchaseClick) },
            onPurchaseClick = {
                event(HomeEvent.OnDoPurchaseClick)
                event(HomeEvent.OnDismissPurchaseClick)
            }
        )
    }
    Scaffold(
        topBar = {
            CustomTopBar(
                title = "Главная",
                isAddIconVisible = true,
                isSettingsIconVisible = true,
                onSettingsIconClick = { event(HomeEvent.OnSettingsClick) },
                onAddIconClick = { event(HomeEvent.OnAddNewClick) }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            item {
                SearchTextField(
                    value = viewModel.searchState,
                    onValueChange = { event(HomeEvent.OnSearchTextChanged(it)) },
                    trailingIconEnabled = viewModel.searchState.isNotEmpty(),
                    onTrailingClick = { event(HomeEvent.OnSearchTextChanged("")) }
                )
            }
            items(cars) { car ->
                CarItem(car, event)
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CarItem(car: Car, event: (HomeEvent) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .height(200.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable { event(HomeEvent.OnItemClick(car.id)) }
    ) {

        GlideImage(
            model = car.photoUri,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            failure = placeholder(R.drawable.image_placeholder)
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black
                        ),
                    )
                )
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp),
            text = car.name,
            textAlign = TextAlign.Center,
            style = TextStyle(color = Color.White)
        )
    }
}
