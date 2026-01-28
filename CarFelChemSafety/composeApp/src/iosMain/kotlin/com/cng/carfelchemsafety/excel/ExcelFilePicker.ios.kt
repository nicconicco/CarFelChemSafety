package com.cng.carfelchemsafety.excel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

@Composable
actual fun ExcelFilePicker(
    show: Boolean,
    onResult: (FilePickerResult) -> Unit
) {
    LaunchedEffect(show) {
        if (show) {
            onResult(FilePickerResult.Error("Seleção de ficheiros ainda não disponível no iOS"))
        }
    }
}
