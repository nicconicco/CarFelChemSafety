package com.cng.carfelchemsafety.screens.workpermit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.cng.carfelchemsafety.auth.WorkPermitResult
import com.cng.carfelchemsafety.util.AppStrings
import com.cng.carfelchemsafety.viewmodel.WorkPermitViewModel

@Composable
fun PermitResultScreen(
    viewModel: WorkPermitViewModel,
    strings: AppStrings,
    onBackToHome: () -> Unit,
    modifier: Modifier = Modifier
) {
    val submitResult by viewModel.submitResult.collectAsState()

    val resultText = when (submitResult) {
        is WorkPermitResult.Success -> strings.resultApproved
        is WorkPermitResult.Error -> (submitResult as WorkPermitResult.Error).message
        else -> strings.resultError
    }

    val resultColor = when (submitResult) {
        is WorkPermitResult.Success -> MaterialTheme.colorScheme.primary
        is WorkPermitResult.Error -> MaterialTheme.colorScheme.error
        else -> MaterialTheme.colorScheme.onSurfaceVariant
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = resultText,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = resultColor,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                viewModel.resetForm()
                onBackToHome()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(strings.backToHome)
        }
    }
}
