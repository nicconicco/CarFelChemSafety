package com.cng.carfelchemsafety.repository

import kotlinx.coroutines.delay

class LocalExcelImportRepository : ExcelImportRepository {

    override suspend fun importEmployees(): Result<Int> {
        delay(2000)
        return Result.success(15)
    }

    override suspend fun importPTData(): Result<Int> {
        delay(2000)
        return Result.success(42)
    }
}
