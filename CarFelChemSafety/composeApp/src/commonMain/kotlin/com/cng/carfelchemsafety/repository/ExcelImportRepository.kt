package com.cng.carfelchemsafety.repository

interface ExcelImportRepository {
    suspend fun importEmployees(): Result<Int>
    suspend fun importPTData(): Result<Int>
}
