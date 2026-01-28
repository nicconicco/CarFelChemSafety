package com.cng.carfelchemsafety.excel

actual object ExcelParser {
    actual fun countRows(bytes: ByteArray): Result<Int> {
        return Result.failure(Exception("Importação de Excel ainda não disponível no iOS"))
    }
}
