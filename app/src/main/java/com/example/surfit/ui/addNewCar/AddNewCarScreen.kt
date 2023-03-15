package com.example.surfit.ui.addNewCar

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.surfit.R
import com.example.surfit.ui.components.CustomTextField
import com.example.surfit.ui.components.CustomTopBar

@Composable
fun AddNewCarScreen(viewModel: AddNewCarViewModel, event: (AddNewCarEvent) -> Unit) {
    Scaffold(topBar = { CustomTopBar(title = "Добавить автомобиль") }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            with(viewModel.screenState) {
                val focusManager = LocalFocusManager.current
                CustomTextField(
                    singleLine = true,
                    modifier = Modifier.padding(vertical = 16.dp),
                    value = name,
                    onValueChange = { event(AddNewCarEvent.OnNameChanged(it)) },
                    placeHolderString = stringResource(R.string.placeholder_input_name),
                    keyboardActions = KeyboardActions(
                        onDone = { focusManager.moveFocus(FocusDirection.Next) }
                    )
                )
                CustomTextField(
                    singleLine = true,
                    modifier = Modifier.padding(vertical = 16.dp),
                    value = year,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    onValueChange = { string ->
                        string.lastOrNull()?.apply {
                            if (isDigit())
                                event(AddNewCarEvent.OnYearChanged(string))
                        }
                    },
                    placeHolderString = stringResource(R.string.placeholder_input_year),
                    keyboardActions = KeyboardActions(
                        onDone = { focusManager.moveFocus(FocusDirection.Next) }
                    )
                )
                CustomTextField(
                    singleLine = true,
                    modifier = Modifier.padding(vertical = 16.dp),
                    value = engineCapacity,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    onValueChange = { string ->
                        string.lastOrNull()?.apply {
                            if (isDigit() || this == '.')
                                event(AddNewCarEvent.OnEngineCapacityChanged(string))
                        }
                    },
                    placeHolderString = stringResource(R.string.placeholder_input_engine_capacity),
                    keyboardActions = KeyboardActions(
                        onDone = { focusManager.clearFocus() }
                    )
                )
                PickImageButton(event)
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { event(AddNewCarEvent.OnSaveClick) }
                ) {
                    Text(text = "Сохранить")
                }
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PickImageButton(event: (AddNewCarEvent) -> Unit) {
    var imageUri by remember { mutableStateOf(Uri.EMPTY) }
    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            imageUri = uri ?: Uri.EMPTY
            event(AddNewCarEvent.OnImageChosen(imageUri))
        }
    )
    if (imageUri == Uri.EMPTY) {
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                imagePicker.launch(
                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                )
            }
        ) {
            Text(text = "Добавить фото")
        }
    } else {
        Column {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                onClick = {
                    imagePicker.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                }
            ) {
                Text(text = "Изменить фото")
            }
            GlideImage(
                model = imageUri,
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
        }
    }
}
