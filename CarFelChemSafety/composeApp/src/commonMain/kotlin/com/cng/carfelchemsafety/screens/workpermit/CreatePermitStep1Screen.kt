package com.cng.carfelchemsafety.screens.workpermit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cng.carfelchemsafety.model.EnvironmentalCategory
import com.cng.carfelchemsafety.util.AppStrings
import com.cng.carfelchemsafety.viewmodel.WorkPermitViewModel

@Composable
fun CreatePermitStep1Screen(
    viewModel: WorkPermitViewModel,
    strings: AppStrings,
    onNext: () -> Unit,
    onCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    val formState by viewModel.formState.collectAsState()
    val totalSteps = 9

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        StepProgressIndicator(
            currentStep = 1,
            totalSteps = totalSteps,
            stepLabel = strings.stepOf.format(1, totalSteps)
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            Text(
                text = strings.step1Title,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = formState.workDescription,
                onValueChange = { viewModel.updateWorkDescription(it) },
                label = { Text(strings.workDescription) },
                modifier = Modifier.fillMaxWidth(),
                minLines = 3
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = strings.environmentalCategory,
                style = MaterialTheme.typography.titleMedium
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = formState.environmentalCategory == EnvironmentalCategory.DOCUMENTOS,
                    onClick = { viewModel.updateEnvironmentalCategory(EnvironmentalCategory.DOCUMENTOS) }
                )
                Text(strings.categoryDocumentos)
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = formState.environmentalCategory == EnvironmentalCategory.ELETRICA,
                    onClick = { viewModel.updateEnvironmentalCategory(EnvironmentalCategory.ELETRICA) }
                )
                Text(strings.categoryEletrica)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = strings.subcategories,
                style = MaterialTheme.typography.titleMedium
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = formState.subcategoryDocumentos,
                    onCheckedChange = { viewModel.updateSubcategoryDocumentos(it) }
                )
                Text(strings.subcatDocumentos)
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = formState.subcategoryEletrica,
                    onCheckedChange = { viewModel.updateSubcategoryEletrica(it) }
                )
                Text(strings.subcatEletrica)
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = formState.subcategoryMecanica,
                    onCheckedChange = { viewModel.updateSubcategoryMecanica(it) }
                )
                Text(strings.subcatMecanica)
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = formState.subcategoryHidraulica,
                    onCheckedChange = { viewModel.updateSubcategoryHidraulica(it) }
                )
                Text(strings.subcatHidraulica)
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = formState.subcategoryCivil,
                    onCheckedChange = { viewModel.updateSubcategoryCivil(it) }
                )
                Text(strings.subcatCivil)
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            TextButton(onClick = onCancel) {
                Text(strings.cancel)
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(onClick = onNext) {
                Text(strings.next)
            }
        }
    }
}
