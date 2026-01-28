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

    private val _showEmployeePicker = MutableStateFlow(false)
    val showEmployeePicker: StateFlow<Boolean> = _showEmployeePicker.asStateFlow()

    private val _showPTPicker = MutableStateFlow(false)
    val showPTPicker: StateFlow<Boolean> = _showPTPicker.asStateFlow()

    fun requestEmployeeFilePicker() {
        _showEmployeePicker.value = true
    }

    fun requestPTFilePicker() {
        _showPTPicker.value = true
    }

    fun onEmployeePickerDismissed() {
        _showEmployeePicker.value = false
    }

    fun onPTPickerDismissed() {
        _showPTPicker.value = false
    }

    fun importEmployees(fileBytes: ByteArray) {
        _showEmployeePicker.value = false
        scope.launch {
            _employeeImportState.value = ExcelImportResult.Loading
            repository.importEmployees(fileBytes)
                .onSuccess { count ->
                    _employeeImportState.value = ExcelImportResult.Success(count, "employees")
                }
                .onFailure { error ->
                    _employeeImportState.value = ExcelImportResult.Error(error.message ?: "Erro desconhecido")
                }
        }
    }

    fun importPTData(fileBytes: ByteArray) {
        _showPTPicker.value = false
        scope.launch {
            _ptImportState.value = ExcelImportResult.Loading
            repository.importPTData(fileBytes)
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
