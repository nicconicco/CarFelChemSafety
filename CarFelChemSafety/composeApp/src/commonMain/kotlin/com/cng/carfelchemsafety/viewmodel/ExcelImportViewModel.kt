package com.cng.carfelchemsafety.viewmodel

import com.cng.carfelchemsafety.auth.ExcelImportResult
import com.cng.carfelchemsafety.repository.ExcelImportRepository
import com.cng.carfelchemsafety.repository.LocalExcelImportRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ExcelImportViewModel(
    private val repository: ExcelImportRepository = LocalExcelImportRepository()
) {
    private val scope = CoroutineScope(Dispatchers.Default)

    private val _employeeImportState = MutableStateFlow<ExcelImportResult>(ExcelImportResult.Idle)
    val employeeImportState: StateFlow<ExcelImportResult> = _employeeImportState.asStateFlow()

    private val _ptImportState = MutableStateFlow<ExcelImportResult>(ExcelImportResult.Idle)
    val ptImportState: StateFlow<ExcelImportResult> = _ptImportState.asStateFlow()

    fun importEmployees() {
        scope.launch {
            _employeeImportState.value = ExcelImportResult.Loading
            repository.importEmployees()
                .onSuccess { count ->
                    _employeeImportState.value = ExcelImportResult.Success(count, "employees")
                }
                .onFailure { error ->
                    _employeeImportState.value = ExcelImportResult.Error(error.message ?: "Erro desconhecido")
                }
        }
    }

    fun importPTData() {
        scope.launch {
            _ptImportState.value = ExcelImportResult.Loading
            repository.importPTData()
                .onSuccess { count ->
                    _ptImportState.value = ExcelImportResult.Success(count, "pts")
                }
                .onFailure { error ->
                    _ptImportState.value = ExcelImportResult.Error(error.message ?: "Erro desconhecido")
                }
        }
    }

    fun resetEmployeeImportState() {
        _employeeImportState.value = ExcelImportResult.Idle
    }

    fun resetPTImportState() {
        _ptImportState.value = ExcelImportResult.Idle
    }
}
