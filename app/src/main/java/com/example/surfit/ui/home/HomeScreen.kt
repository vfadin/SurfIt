package com.example.surfit.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.surfit.domain.entity.Car
import com.example.surfit.ui.components.SearchTextField

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel, event: (HomeEvent) -> Unit) {
    val cars by viewModel.carsStateFlow.collectAsState(emptyList())
    val coroutineScope = rememberCoroutineScope()
    val modalSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Expanded)
    ModalBottomSheetLayout(
        sheetContent = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(16.dp)
            ) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = 4.dp,
                    backgroundColor = Color.LightGray
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "Payment details", fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(text = "Amount: $9.99")
                        Text(text = "Payment method: Credit Card")
                    }
                }
                Button(modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp), onClick = { /*TODO*/ }) {
                    Text(text = "Pay")
                }
                Spacer(modifier = Modifier.height(40.dp))
            }
        },
        sheetState = modalSheetState
    ) {
        Scaffold { paddingValues ->
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
}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CarItem(car: Car, event: (HomeEvent) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable { event(HomeEvent.OnItemClick) }
    ) {
        GlideImage(
            model = "https://www.mentoday.ru/upload/img_cache/411/41138d75d07cc38b36610e25985734a7_ce_1518x1012x117x0.png",
            contentDescription = null,
            contentScale = ContentScale.Crop
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
