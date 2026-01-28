package com.cng.carfelchemsafety.excel

import androidx.compose.runtime.Composable

@Composable
expect fun ExcelFilePicker(
    show: Boolean,
    onResult: (FilePickerResult) -> Unit
)
