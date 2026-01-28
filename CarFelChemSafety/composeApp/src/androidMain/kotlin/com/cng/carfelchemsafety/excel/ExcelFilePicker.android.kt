package com.cng.carfelchemsafety.excel

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext

@Composable
actual fun ExcelFilePicker(
    show: Boolean,
    onResult: (FilePickerResult) -> Unit
) {
    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ) { uri: Uri? ->
        if (uri == null) {
            onResult(FilePickerResult.Cancelled)
            return@rememberLauncherForActivityResult
        }
        try {
            val contentResolver = context.contentResolver
            val bytes = contentResolver.openInputStream(uri)?.use { it.readBytes() }
            if (bytes == null) {
                onResult(FilePickerResult.Error("Não foi possível ler o ficheiro"))
                return@rememberLauncherForActivityResult
            }
            // Extract file name from URI
            val fileName = uri.lastPathSegment ?: "unknown.xlsx"
            onResult(FilePickerResult.Success(bytes, fileName))
        } catch (e: Exception) {
            onResult(FilePickerResult.Error("Erro ao ler ficheiro: ${e.message}"))
        }
    }

    LaunchedEffect(show) {
        if (show) {
            launcher.launch(
                arrayOf(
                    "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", // .xlsx
                    "application/vnd.ms-excel" // .xls
                )
            )
        }
    }
}
