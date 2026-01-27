package com.cng.carfelchemsafety.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cng.carfelchemsafety.auth.PasswordChangeResult
import com.cng.carfelchemsafety.auth.ProfileUpdateResult
import com.cng.carfelchemsafety.model.User
import com.cng.carfelchemsafety.util.AppStrings

private const val SECRET_PASSWORD = "0000"

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MyAccountScreen(
    currentUser: User?,
    profileUpdateState: ProfileUpdateResult,
    passwordChangeState: PasswordChangeResult,
    strings: AppStrings,
    onSaveClick: (username: String, email: String) -> Unit,
    onChangePasswordClick: (currentPassword: String, newPassword: String, confirmPassword: String) -> Unit,
    onLogoutClick: () -> Unit,
    onBack: () -> Unit,
    onSecretAccessGranted: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    var username by remember(currentUser) { mutableStateOf(currentUser?.username ?: "") }
    var email by remember(currentUser) { mutableStateOf(currentUser?.email ?: "") }
    var showProfileSuccess by remember { mutableStateOf(false) }

    var currentPassword by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }
    var confirmNewPassword by remember { mutableStateOf("") }
    var showPasswordSuccess by remember { mutableStateOf(false) }

    // Secret access dialog state
    var showSecretDialog by remember { mutableStateOf(false) }
    var secretPassword by remember { mutableStateOf("") }
    var secretPasswordError by remember { mutableStateOf(false) }

    // Secret password dialog
    if (showSecretDialog) {
        AlertDialog(
            onDismissRequest = {
                showSecretDialog = false
                secretPassword = ""
                secretPasswordError = false
            },
            title = {
                Text(
                    text = strings.restrictedAccess,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Column {
                    Text(
                        text = strings.enterPasswordToContinue,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    OutlinedTextField(
                        value = secretPassword,
                        onValueChange = {
                            secretPassword = it
                            secretPasswordError = false
                        },
                        label = { Text(strings.password) },
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                        singleLine = true,
                        isError = secretPasswordError,
                        modifier = Modifier.fillMaxWidth()
                    )
                    if (secretPasswordError) {
                        Text(
                            text = strings.incorrectPassword,
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (secretPassword == SECRET_PASSWORD) {
                            showSecretDialog = false
                            secretPassword = ""
                            secretPasswordError = false
                            onSecretAccessGranted()
                        } else {
                            secretPasswordError = true
                        }
                    }
                ) {
                    Text(strings.enterButton)
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showSecretDialog = false
                        secretPassword = ""
                        secretPasswordError = false
                    }
                ) {
                    Text(strings.cancel)
                }
            }
        )
    }

    LaunchedEffect(profileUpdateState) {
        if (profileUpdateState is ProfileUpdateResult.Success) {
            showProfileSuccess = true
        }
    }

    LaunchedEffect(passwordChangeState) {
        if (passwordChangeState is PasswordChangeResult.Success) {
            showPasswordSuccess = true
            currentPassword = ""
            newPassword = ""
            confirmNewPassword = ""
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 80.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Back button
        TextButton(
            onClick = onBack,
            modifier = Modifier.align(Alignment.Start)
        ) {
            Text(strings.back)
        }

        // Avatar with long press for secret access
        Surface(
            modifier = Modifier
                .size(100.dp)
                .combinedClickable(
                    onClick = { },
                    onLongClick = { showSecretDialog = true }
                ),
            shape = CircleShape,
            color = MaterialTheme.colorScheme.primaryContainer
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = currentUser?.username?.firstOrNull()?.uppercase() ?: "?",
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        }

        Text(
            text = strings.myProfile,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        // Profile Form Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = strings.personalInfo,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )

                OutlinedTextField(
                    value = username,
                    onValueChange = { username = it },
                    label = { Text(strings.username) },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    enabled = profileUpdateState !is ProfileUpdateResult.Loading
                )

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text(strings.email) },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    enabled = profileUpdateState !is ProfileUpdateResult.Loading
                )

                if (profileUpdateState is ProfileUpdateResult.Error) {
                    Text(
                        text = profileUpdateState.message,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                if (showProfileSuccess) {
                    Text(
                        text = strings.profileUpdatedSuccess,
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                Button(
                    onClick = {
                        showProfileSuccess = false
                        onSaveClick(username, email)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = profileUpdateState !is ProfileUpdateResult.Loading
                ) {
                    if (profileUpdateState is ProfileUpdateResult.Loading) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(20.dp),
                            color = MaterialTheme.colorScheme.onPrimary,
                            strokeWidth = 2.dp
                        )
                    } else {
                        Text(strings.saveProfile)
                    }
                }
            }
        }

        // Password Change Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = strings.changePasswordTitle,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )

                OutlinedTextField(
                    value = currentPassword,
                    onValueChange = { currentPassword = it },
                    label = { Text(strings.currentPasswordLabel) },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation(),
                    enabled = passwordChangeState !is PasswordChangeResult.Loading
                )

                OutlinedTextField(
                    value = newPassword,
                    onValueChange = { newPassword = it },
                    label = { Text(strings.newPasswordLabel) },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation(),
                    enabled = passwordChangeState !is PasswordChangeResult.Loading
                )

                OutlinedTextField(
                    value = confirmNewPassword,
                    onValueChange = { confirmNewPassword = it },
                    label = { Text(strings.confirmNewPasswordLabel) },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation(),
                    enabled = passwordChangeState !is PasswordChangeResult.Loading
                )

                if (passwordChangeState is PasswordChangeResult.Error) {
                    Text(
                        text = passwordChangeState.message,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                if (showPasswordSuccess) {
                    Text(
                        text = strings.passwordChangedSuccess,
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                OutlinedButton(
                    onClick = {
                        showPasswordSuccess = false
                        onChangePasswordClick(currentPassword, newPassword, confirmNewPassword)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = passwordChangeState !is PasswordChangeResult.Loading
                ) {
                    if (passwordChangeState is PasswordChangeResult.Loading) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(20.dp),
                            strokeWidth = 2.dp
                        )
                    } else {
                        Text(strings.changePasswordButton)
                    }
                }
            }
        }

        // ID info (read-only)
        if (currentUser != null) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = strings.accountIdLabel,
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = currentUser.id,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Logout button
        Button(
            onClick = onLogoutClick,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.error
            )
        ) {
            Text(strings.logoutAccount)
        }
    }
}
