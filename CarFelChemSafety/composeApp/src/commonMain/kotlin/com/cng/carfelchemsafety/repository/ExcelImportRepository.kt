package com.cng.carfelchemsafety.repository

interface ExcelImportRepository {
    suspend fun importEmployees(fileBytes: ByteArray): Result<Int>
    suspend fun importPTData(fileBytes: ByteArray): Result<Int>
}
