package com.example.surfit.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.surfit.ui.theme.White

@Composable
fun PurchaseDialog(
    title: String,
    onDismissClick: () -> Unit,
    onBackClick: () -> Unit,
    onPurchaseClick: () -> Unit,
) {
    Dialog(onDismissRequest = { onDismissClick() }) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(16.dp)
            ) {
                Text(
                    text = title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    textAlign = TextAlign.Center
                )
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
                        Text(text = "Детали оплаты", fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(text = "Стоимость: $9.99")
                        Text(text = "Период действия: 'Бессрочно'")
                    }
                }
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        modifier = Modifier
                            .padding(vertical = 16.dp)
                            .weight(.5f),
                        onClick = { onBackClick() }) {
                        Text(text = "Нет, спасибо")
                    }
                    Button(
                        modifier = Modifier
                            .padding(vertical = 16.dp)
                            .weight(.5f),
                        onClick = { onPurchaseClick() }) {
                        Text(text = "Купить")
                    }
                }
            }
        }
    }
}