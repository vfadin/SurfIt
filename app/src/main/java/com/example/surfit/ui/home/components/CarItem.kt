package com.example.surfit.ui.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import com.example.surfit.ui.home.HomeEvent

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