package com.example.surfit.ui.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.surfit.ui.theme.White

@Composable
fun SortsDialog(
    selectedIndex: Int,
    sorts: List<String>,
    onDismissClick: () -> Unit,
    onSortChosen: (Int) -> Unit,
) {
    Dialog(onDismissRequest = { onDismissClick() }) {
        var selected by remember { mutableStateOf(selectedIndex) }
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(White)
        ) {
            item {
                Text(
                    text = "Сортировать",
                    style = MaterialTheme.typography.h2,
                    modifier = Modifier.padding(16.dp)
                )
            }
            items(sorts.size) {
                SortItem(selected == it, sorts[it]) {
                    selected = if (selected != it) {
                        onSortChosen(it)
                        it
                    } else {
                        -1
                    }

                }
            }
            item {
                Spacer(Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun SortItem(selected: Boolean, name: String, onSortChosen: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onSortChosen() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selected,
            onClick = { onSortChosen() }
        )
        Text(text = name)
    }
}
