package com.cng.carfelchemsafety.screens.workpermit

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.cng.carfelchemsafety.util.AppStrings
import com.cng.carfelchemsafety.viewmodel.WorkPermitViewModel

@Composable
fun CreatePermitStep9Screen(
    viewModel: WorkPermitViewModel,
    strings: AppStrings,
    createdBy: String,
    onSendForApproval: () -> Unit,
    onPrevious: () -> Unit,
    modifier: Modifier = Modifier
) {
    val formState by viewModel.formState.collectAsState()
    val totalSteps = 9
    var showSummary by remember { mutableStateOf(false) }

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        StepProgressIndicator(
            currentStep = 9,
            totalSteps = totalSteps,
            stepLabel = strings.stepOf.format(9, totalSteps)
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            Text(
                text = strings.step9Title,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Order summary reference box
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.outlineVariant,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(12.dp)
            ) {
                Text(
                    text = strings.orderSummaryLabel,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(4.dp))

                val stepsRef = buildString {
                    append("edit")
                    append(", 01") // Step 1
                    append(", 01") // Step 2
                    if (formState.exposureOptions.isNotEmpty()) append(", 03")
                    if (formState.photoTaken) append(", 04")
                    if (formState.requirement1 || formState.requirement2 || formState.requirement3) append(", 05")
                    if (formState.responsibleName.isNotBlank()) append(", 06")
                    if (formState.associatedWorkType != null) append(", 07")
                    if (formState.heightActivities.isNotBlank()) append(", 08")
                }
                Text(
                    text = "$stepsRef ...",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // View order summary button
            OutlinedButton(
                onClick = { showSummary = !showSummary },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(strings.viewOrderSummary)
            }

            // Collapsible summary
            if (showSummary) {
                Spacer(modifier = Modifier.height(8.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.outlineVariant,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(12.dp)
                ) {
                    Text(
                        text = strings.reviewSummary,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    SummaryItem(strings.workDescription, formState.workDescription)
                    SummaryItem(strings.environmentalCategory, formState.environmentalCategory?.name ?: "-")
                    SummaryItem(strings.documentDetails, formState.documentDetails)
                    SummaryItem(strings.responsibleName, formState.responsibleName)
                    SummaryItem(strings.responsibleRole, formState.responsibleRole)
                    if (formState.associatedWorkType != null) {
                        SummaryItem(strings.associatedWorks, formState.associatedWorkType?.name ?: "-")
                    }
                    if (formState.observations.isNotBlank()) {
                        SummaryItem(strings.observationsLabel, formState.observations)
                    }
                }
            }

            HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))

            // Approver field
            OutlinedTextField(
                value = formState.approverName,
                onValueChange = { viewModel.updateApproverName(it) },
                label = { Text(strings.approverLabel) },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Area responsible field
            OutlinedTextField(
                value = formState.areaResponsible,
                onValueChange = { viewModel.updateAreaResponsible(it) },
                label = { Text(strings.areaResponsible) },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Single signature checkbox
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = formState.singleSignature,
                    onCheckedChange = { viewModel.updateSingleSignature(it) }
                )
                Text(strings.singleSignature)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Send for approval button
            Button(
                onClick = {
                    viewModel.submitPermit(createdBy)
                    onSendForApproval()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(strings.sendForApproval)
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
        }
    }
}

@Composable
private fun SummaryItem(label: String, value: String) {
    if (value.isNotBlank()) {
        Column(modifier = Modifier.padding(vertical = 4.dp)) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = value,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
