package com.cng.carfelchemsafety.excel

expect object ExcelParser {
    fun countRows(bytes: ByteArray): Result<Int>
}
