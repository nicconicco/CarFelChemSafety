package com.cng.carfelchemsafety.repository

import com.cng.carfelchemsafety.excel.ExcelParser

class LocalExcelImportRepository : ExcelImportRepository {

    override suspend fun importEmployees(fileBytes: ByteArray): Result<Int> {
        return ExcelParser.countRows(fileBytes)
    }

    override suspend fun importPTData(fileBytes: ByteArray): Result<Int> {
        return ExcelParser.countRows(fileBytes)
    }
}
