package com.cng.carfelchemsafety.excel

sealed class FilePickerResult {
    data class Success(val bytes: ByteArray, val fileName: String) : FilePickerResult()
    data class Error(val message: String) : FilePickerResult()
    data object Cancelled : FilePickerResult()
}
