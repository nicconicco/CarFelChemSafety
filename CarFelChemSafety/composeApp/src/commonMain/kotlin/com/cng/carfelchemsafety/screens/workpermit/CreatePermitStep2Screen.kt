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
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cng.carfelchemsafety.util.AppStrings
import com.cng.carfelchemsafety.viewmodel.WorkPermitViewModel

@Composable
fun CreatePermitStep2Screen(
    viewModel: WorkPermitViewModel,
    strings: AppStrings,
    onNext: () -> Unit,
    onPrevious: () -> Unit,
    modifier: Modifier = Modifier
) {
    val formState by viewModel.formState.collectAsState()
    val totalSteps = 9

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        StepProgressIndicator(
            currentStep = 2,
            totalSteps = totalSteps,
            stepLabel = strings.stepOf.format(2, totalSteps)
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            Text(
                text = strings.step2Title,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = formState.documentDetails,
                onValueChange = { viewModel.updateDocumentDetails(it) },
                label = { Text(strings.documentDetails) },
                modifier = Modifier.fillMaxWidth(),
                minLines = 3
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = formState.supervisionExecution,
                onValueChange = { viewModel.updateSupervisionExecution(it) },
                label = { Text(strings.supervisionExecution) },
                modifier = Modifier.fillMaxWidth(),
                minLines = 2
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = strings.itemsToCheck,
                style = MaterialTheme.typography.titleMedium
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = formState.checkItem1,
                    onCheckedChange = { viewModel.updateCheckItem1(it) }
                )
                Text(strings.checkItem1)
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = formState.checkItem2,
                    onCheckedChange = { viewModel.updateCheckItem2(it) }
                )
                Text(strings.checkItem2)
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = formState.checkItem3,
                    onCheckedChange = { viewModel.updateCheckItem3(it) }
                )
                Text(strings.checkItem3)
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = formState.checkItem4,
                    onCheckedChange = { viewModel.updateCheckItem4(it) }
                )
                Text(strings.checkItem4)
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = formState.checkItem5,
                    onCheckedChange = { viewModel.updateCheckItem5(it) }
                )
                Text(strings.checkItem5)
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            TextButton(onClick = onPrevious) {
                Text(strings.previous)
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(onClick = onNext) {
                Text(strings.next)
            }
        }
    }
}
