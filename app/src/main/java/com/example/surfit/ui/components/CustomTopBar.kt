package com.example.surfit.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.surfit.R
import com.example.surfit.ui.theme.PlaceholderGray
import com.example.surfit.ui.theme.White

@Composable
fun CustomTopBar(
    title: String,
    isBackIconVisible: Boolean = false,
    onBackIconClick: () -> Unit = {},
    isAddIconVisible: Boolean = false,
    onAddIconClick: () -> Unit = {},
    onSettingsIconClick: () -> Unit = {},
) {
    TopAppBar(backgroundColor = White) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Row(Modifier.align(Alignment.CenterStart)) {
                if (isBackIconVisible) {
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .clickable { onBackIconClick() },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_arrow_back),
                            contentDescription = null,
                            tint = PlaceholderGray
                        )
                    }
                }
                Text(text = title, modifier = Modifier.padding(start = 16.dp))
            }
            Row(modifier = Modifier.align(Alignment.CenterEnd)) {
                if (isAddIconVisible) {
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .clickable { onAddIconClick() },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_add),
                            contentDescription = null,
                            tint = PlaceholderGray
                        )
                    }
                }
                Spacer(modifier = Modifier.width(16.dp))
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { onSettingsIconClick() },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_settings),
                        contentDescription = null,
                        tint = PlaceholderGray
                    )
                }
            }
        }
    }
}