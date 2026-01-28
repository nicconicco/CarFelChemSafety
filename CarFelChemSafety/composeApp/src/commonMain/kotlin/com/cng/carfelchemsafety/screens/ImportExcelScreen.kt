package com.cng.carfelchemsafety.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.cng.carfelchemsafety.auth.ExcelImportResult
import com.cng.carfelchemsafety.excel.ExcelFilePicker
import com.cng.carfelchemsafety.excel.FilePickerResult
import com.cng.carfelchemsafety.util.AppStrings
import com.cng.carfelchemsafety.viewmodel.ExcelImportViewModel

@Composable
fun MenuAdminScreen(
    viewModel: ExcelImportViewModel,
    strings: AppStrings,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val employeeState by viewModel.employeeImportState.collectAsState()
    val ptState by viewModel.ptImportState.collectAsState()
    val showEmployeePicker by viewModel.showEmployeePicker.collectAsState()
    val showPTPicker by viewModel.showPTPicker.collectAsState()

    // Employee file picker
    ExcelFilePicker(show = showEmployeePicker) { result ->
        when (result) {
            is FilePickerResult.Success -> viewModel.importEmployees(result.bytes)
            is FilePickerResult.Error -> {
                viewModel.onEmployeePickerDismissed()
            }
            is FilePickerResult.Cancelled -> {
                viewModel.onEmployeePickerDismissed()
            }
        }
    }

    // PT Data file picker
    ExcelFilePicker(show = showPTPicker) { result ->
        when (result) {
            is FilePickerResult.Success -> viewModel.importPTData(result.bytes)
            is FilePickerResult.Error -> {
                viewModel.onPTPickerDismissed()
            }
            is FilePickerResult.Cancelled -> {
                viewModel.onPTPickerDismissed()
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = strings.importExcelTitle,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center
        )

        Text(
            text = strings.importExcelSubtitle,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Import Employees Card
        ImportCard(
            title = strings.importEmployeesTitle,
            description = strings.importEmployeesDesc,
            buttonText = strings.importButton,
            isLoading = employeeState is ExcelImportResult.Loading,
            onClick = { viewModel.requestEmployeeFilePicker() }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Import PT Data Card
        ImportCard(
            title = strings.importPTDataTitle,
            description = strings.importPTDataDesc,
            buttonText = strings.importButton,
            isLoading = ptState is ExcelImportResult.Loading,
            onClick = { viewModel.requestPTFilePicker() }
        )

        Spacer(modifier = Modifier.weight(1f))

        TextButton(onClick = onBack) {
            Text(
                text = strings.back,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }

    // Employee import result dialog
    when (val state = employeeState) {
        is ExcelImportResult.Success -> {
            AlertDialog(
                onDismissRequest = { viewModel.resetEmployeeImportState() },
                title = { Text(strings.importSuccessTitle) },
                text = { Text(strings.importEmployeesSuccess.replace("%d", state.count.toString())) },
                confirmButton = {
                    TextButton(onClick = { viewModel.resetEmployeeImportState() }) {
                        Text("OK")
                    }
                }
            )
        }
        is ExcelImportResult.Error -> {
            AlertDialog(
                onDismissRequest = { viewModel.resetEmployeeImportState() },
                title = { Text(strings.importErrorTitle) },
                text = { Text(state.message) },
                confirmButton = {
                    TextButton(onClick = { viewModel.resetEmployeeImportState() }) {
                        Text("OK")
                    }
                }
            )
        }
        else -> {}
    }

    // PT import result dialog
    when (val state = ptState) {
        is ExcelImportResult.Success -> {
            AlertDialog(
                onDismissRequest = { viewModel.resetPTImportState() },
                title = { Text(strings.importSuccessTitle) },
                text = { Text(strings.importPTDataSuccess.replace("%d", state.count.toString())) },
                confirmButton = {
                    TextButton(onClick = { viewModel.resetPTImportState() }) {
                        Text("OK")
                    }
                }
            )
        }
        is ExcelImportResult.Error -> {
            AlertDialog(
                onDismissRequest = { viewModel.resetPTImportState() },
                title = { Text(strings.importErrorTitle) },
                text = { Text(state.message) },
                confirmButton = {
                    TextButton(onClick = { viewModel.resetPTImportState() }) {
                        Text("OK")
                    }
                }
            )
        }
        else -> {}
    }
}

@Composable
private fun ImportCard(
    title: String,
    description: String,
    buttonText: String,
    isLoading: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Button(
                onClick = onClick,
                enabled = !isLoading,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            ) {
                if (isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(20.dp),
                        color = MaterialTheme.colorScheme.onPrimary,
                        strokeWidth = 2.dp
                    )
                } else {
                    Text(text = buttonText)
                }
            }
        }
    }
}
