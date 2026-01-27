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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cng.carfelchemsafety.util.AppStrings
import com.cng.carfelchemsafety.viewmodel.WorkPermitViewModel

@Composable
fun CreatePermitStep8Screen(
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
            currentStep = 8,
            totalSteps = totalSteps,
            stepLabel = strings.stepOf.format(8, totalSteps)
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            Text(
                text = strings.step8Title,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = formState.heightActivities,
                onValueChange = { viewModel.updateHeightActivities(it) },
                label = { Text(strings.heightActivities) },
                modifier = Modifier.fillMaxWidth(),
                minLines = 3
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = formState.anchorageInfo,
                onValueChange = { viewModel.updateAnchorageInfo(it) },
                label = { Text(strings.anchorageInfo) },
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
