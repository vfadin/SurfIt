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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.surfit.R
import com.example.surfit.ui.components.CustomTopBar
import com.example.surfit.ui.components.PurchaseDialog
import com.example.surfit.ui.components.SearchTextField
import com.example.surfit.ui.home.components.CarItem
import com.example.surfit.ui.home.components.SortsDialog
import com.example.surfit.ui.theme.BlueBlack
import com.example.surfit.ui.theme.BorderGray

@Composable
fun HomeScreen(viewModel: HomeViewModel, event: (HomeEvent) -> Unit) {
    val cars by viewModel.carsStateFlow.collectAsState(emptyList())
    rememberCoroutineScope()
    val isPurchaseDialogVisible = viewModel.purchaseDialogVisible
    var isSortsDialogVisible by remember { mutableStateOf(false) }
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
    if (isSortsDialogVisible) {
        SortsDialog(
            selectedIndex = viewModel.chosenSortIndex,
            sorts = viewModel.sortList.map {
                when (it) {
                    Sorts.Name -> "По названию"
                    Sorts.Year -> "По году выпуска"
                    Sorts.EngineCapacity -> "По объему двигателя"
                    Sorts.Default -> "По умолчанию"
                }
            },
            onDismissClick = { isSortsDialogVisible = false }
        ) {
            viewModel.onSortChosen(it)
        }
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
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    SearchTextField(
                        modifier = Modifier.weight(1f),
                        value = viewModel.searchState,
                        onValueChange = { event(HomeEvent.OnSearchTextChanged(it)) },
                        trailingIconEnabled = viewModel.searchState.isNotEmpty(),
                        onTrailingClick = { event(HomeEvent.OnSearchTextChanged("")) }
                    )
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .background(BorderGray)
                            .clickable { isSortsDialogVisible = true },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            tint = BlueBlack,
                            painter = painterResource(R.drawable.ic_sort),
                            contentDescription = null
                        )
                    }
                }
            }
            items(cars) { car ->
                CarItem(car, event)
            }
        }
    }
}
