package com.example.surfit.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.surfit.R
import com.example.surfit.ui.theme.PlaceholderGray

@Composable
fun SearchTextField(
    modifier: Modifier = Modifier,
    value: String = "",
    onValueChange: (String) -> Unit = {},
    trailingIconEnabled: Boolean = false,
    onTrailingClick: () -> Unit = {},
    onSearch: () -> Unit = {}
) {
    CustomTextField(
        value = value,
        onValueChange = { onValueChange(it) },
        modifier = modifier,
        contentTextStyle = MaterialTheme.typography.body1,
        placeHolderString = stringResource(R.string.search_placeholder),
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = null,
                modifier = Modifier.size(16.dp),
                tint = PlaceholderGray
            )
        },
        trailingIconEnabled = trailingIconEnabled,
        trailingIcon = {
            Icon(
                imageVector = Icons.Filled.Close,
                contentDescription = null,
                modifier = Modifier.size(16.dp),
                tint = PlaceholderGray
            )
        },
        trailingClickable = { onTrailingClick() },
        type = FieldType.Filled,
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearch()
            },
        ),
    )
}