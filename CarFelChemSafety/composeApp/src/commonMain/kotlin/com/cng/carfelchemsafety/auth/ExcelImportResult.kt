package com.cng.carfelchemsafety.auth

sealed class ExcelImportResult {
    data object Idle : ExcelImportResult()
    data object Loading : ExcelImportResult()
    data class Success(val count: Int, val type: String) : ExcelImportResult()
    data class Error(val message: String) : ExcelImportResult()
}
