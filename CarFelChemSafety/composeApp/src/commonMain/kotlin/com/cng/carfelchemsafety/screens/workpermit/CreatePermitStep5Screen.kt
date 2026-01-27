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
fun CreatePermitStep5Screen(
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
            currentStep = 5,
            totalSteps = totalSteps,
            stepLabel = strings.stepOf.format(5, totalSteps)
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            Text(
                text = strings.step5Title,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = strings.requirementsChecklist,
                style = MaterialTheme.typography.titleMedium
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = formState.requirement1,
                    onCheckedChange = { viewModel.updateRequirement1(it) }
                )
                Text(strings.req1)
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = formState.requirement2,
                    onCheckedChange = { viewModel.updateRequirement2(it) }
                )
                Text(strings.req2)
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = formState.requirement3,
                    onCheckedChange = { viewModel.updateRequirement3(it) }
                )
                Text(strings.req3)
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = formState.requirement4,
                    onCheckedChange = { viewModel.updateRequirement4(it) }
                )
                Text(strings.req4)
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = formState.requirement5,
                    onCheckedChange = { viewModel.updateRequirement5(it) }
                )
                Text(strings.req5)
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = formState.requirement6,
                    onCheckedChange = { viewModel.updateRequirement6(it) }
                )
                Text(strings.req6)
            }

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = formState.observations,
                onValueChange = { viewModel.updateObservations(it) },
                label = { Text(strings.observationsLabel) },
                modifier = Modifier.fillMaxWidth(),
                minLines = 3
            )
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
