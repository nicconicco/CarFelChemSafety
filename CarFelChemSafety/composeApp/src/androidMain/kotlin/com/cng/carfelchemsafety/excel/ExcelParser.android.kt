package com.cng.carfelchemsafety.excel

import org.apache.poi.ss.usermodel.WorkbookFactory
import java.io.ByteArrayInputStream

actual object ExcelParser {
    actual fun countRows(bytes: ByteArray): Result<Int> {
        return try {
            val workbook = WorkbookFactory.create(ByteArrayInputStream(bytes))
            val sheet = workbook.getSheetAt(0)
            val totalRows = sheet.physicalNumberOfRows
            workbook.close()
            // Subtract 1 for the header row
            val dataRows = if (totalRows > 0) totalRows - 1 else 0
            Result.success(dataRows)
        } catch (e: Exception) {
            Result.failure(Exception("Erro ao ler ficheiro Excel: ${e.message}"))
        }
    }
}
