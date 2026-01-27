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
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cng.carfelchemsafety.model.EliminationOption
import com.cng.carfelchemsafety.model.ExposureOption
import com.cng.carfelchemsafety.util.AppStrings
import com.cng.carfelchemsafety.viewmodel.WorkPermitViewModel

@Composable
fun CreatePermitStep3Screen(
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
            currentStep = 3,
            totalSteps = totalSteps,
            stepLabel = strings.stepOf.format(3, totalSteps)
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            Text(
                text = strings.step3Title,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = strings.exposureOptions,
                style = MaterialTheme.typography.titleMedium
            )

            val exposureItems = listOf(
                ExposureOption.RUIDO to strings.exposureRuido,
                ExposureOption.POEIRA to strings.exposurePoeira,
                ExposureOption.QUIMICO to strings.exposureQuimico,
                ExposureOption.BIOLOGICO to strings.exposureBiologico,
                ExposureOption.RADIACAO to strings.exposureRadiacao,
                ExposureOption.VIBRAÇÃO to strings.exposureVibracao,
                ExposureOption.CALOR to strings.exposureCalor,
                ExposureOption.FRIO to strings.exposureFrio
            )

            exposureItems.forEach { (option, label) ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = formState.exposureOptions.contains(option),
                        onCheckedChange = { viewModel.toggleExposureOption(option) }
                    )
                    Text(label)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = strings.eliminationOptions,
                style = MaterialTheme.typography.titleMedium
            )

            val eliminationItems = listOf(
                EliminationOption.CORTE to strings.eliminationCorte,
                EliminationOption.CHOQUE to strings.eliminationChoque,
                EliminationOption.CURTO_CIRCUITO to strings.eliminationCurtoCircuito,
                EliminationOption.ARCO_ELETRICO to strings.eliminationArcoEletrico,
                EliminationOption.BLOQUEIO to strings.eliminationBloqueio,
                EliminationOption.SINALIZACAO to strings.eliminationSinalizacao
            )

            eliminationItems.forEach { (option, label) ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = formState.eliminationOptions.contains(option),
                        onCheckedChange = { viewModel.toggleEliminationOption(option) }
                    )
                    Text(label)
                }
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
