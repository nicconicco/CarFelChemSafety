package com.cng.carfelchemsafety.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import com.cng.carfelchemsafety.model.UserRole
import com.cng.carfelchemsafety.util.AppStrings
import com.cng.carfelchemsafety.viewmodel.SharedLoginViewModel

@Composable
fun HomeScreen(
    viewModel: SharedLoginViewModel,
    strings: AppStrings,
    userRole: UserRole,
    onLogout: () -> Unit,
    onCreatePT: () -> Unit,
    onMyPermits: () -> Unit,
    onMyApprovals: () -> Unit,
    onMyAccount: () -> Unit,
    onImportExcel: () -> Unit,
    modifier: Modifier = Modifier
) {
    val currentUser by viewModel.currentUser.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "${strings.homeGreeting}, ${currentUser?.username ?: ""}",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center
        )

        Text(
            text = strings.homeHowCanWeHelp,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(40.dp))

        // First row: always Criar PT + Minhas PTs
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            MenuCard(
                title = strings.menuCreatePT,
                onClick = onCreatePT,
                modifier = Modifier.weight(1f)
            )
            MenuCard(
                title = strings.menuMyPTs,
                onClick = onMyPermits,
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        //todo: after update in firebase check this
//        if (userRole == UserRole.MANAGER) {
        if (userRole == UserRole.COMMON) {
            // MANAGER: second row with Aprovacoes + Minha Conta
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                MenuCard(
                    title = strings.menuMyApprovals,
                    onClick = onMyApprovals,
                    modifier = Modifier.weight(1f)
                )
                MenuCard(
                    title = strings.menuMyAccount,
                    onClick = onMyAccount,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Import Excel button (full-width)
            Button(
                onClick = onImportExcel,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.tertiary,
                    contentColor = MaterialTheme.colorScheme.onTertiary
                )
            ) {
                Text(
                    text = strings.menuImportExcel,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )
            }
        } else {
            // COMMON: only Minha Conta
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                MenuCard(
                    title = strings.menuMyAccount,
                    onClick = onMyAccount,
                    modifier = Modifier.weight(1f)
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        TextButton(
            onClick = {
                viewModel.logout()
                onLogout()
            }
        ) {
            Text(
                text = strings.logoutButton,
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}

@Composable
private fun MenuCard(
    title: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        modifier = modifier.height(120.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                textAlign = TextAlign.Center
            )
        }
    }
}
