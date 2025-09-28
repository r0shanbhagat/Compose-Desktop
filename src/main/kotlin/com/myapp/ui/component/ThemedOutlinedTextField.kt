package com.myapp.ui.component

import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation
import com.myapp.ui.value.R

@Composable
fun ThemedOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: @Composable () -> Unit,
    isError: Boolean = false,
    modifier: Modifier = Modifier,
    singleLine: Boolean = true,
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {
    val outlinedTextFieldColors = TextFieldDefaults.outlinedTextFieldColors(
        textColor = R.color.Primay,
        focusedBorderColor = R.color.Primay,
        unfocusedBorderColor = R.color.Primay,
        cursorColor = R.color.Primay,
        focusedLabelColor = R.color.Primay,
        unfocusedLabelColor = R.color.Primay,
        errorBorderColor = MaterialTheme.colors.error,
        errorCursorColor = MaterialTheme.colors.error,
        errorLabelColor = MaterialTheme.colors.error
    )


    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = label,
        isError = isError,
        singleLine = singleLine,
        visualTransformation = visualTransformation,
        colors = outlinedTextFieldColors,
        modifier = modifier
    )
}