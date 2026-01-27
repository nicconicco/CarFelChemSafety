package com.cng.carfelchemsafety

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.cng.carfelchemsafety.auth.AuthResult
import com.cng.carfelchemsafety.auth.PasswordRecoveryResult
import com.cng.carfelchemsafety.auth.RegisterResult
import com.cng.carfelchemsafety.navigation.Screen
import com.cng.carfelchemsafety.screens.ErrorDialog
import com.cng.carfelchemsafety.screens.ForgotPasswordScreen
import com.cng.carfelchemsafety.screens.HomeScreen
import com.cng.carfelchemsafety.screens.ImportExcelScreen
import com.cng.carfelchemsafety.screens.LoginScreen
import com.cng.carfelchemsafety.screens.MyAccountScreen
import com.cng.carfelchemsafety.screens.MyApprovalsScreen
import com.cng.carfelchemsafety.screens.MyPermitsScreen
import com.cng.carfelchemsafety.screens.RegisterFormData
import com.cng.carfelchemsafety.screens.RegisterScreen
import com.cng.carfelchemsafety.screens.TempPasswordScreen
import com.cng.carfelchemsafety.screens.TermsScreen
import com.cng.carfelchemsafety.screens.workpermit.CreatePermitStep1Screen
import com.cng.carfelchemsafety.screens.workpermit.CreatePermitStep2Screen
import com.cng.carfelchemsafety.screens.workpermit.CreatePermitStep3Screen
import com.cng.carfelchemsafety.screens.workpermit.CreatePermitStep4Screen
import com.cng.carfelchemsafety.screens.workpermit.CreatePermitStep5Screen
import com.cng.carfelchemsafety.screens.workpermit.CreatePermitStep6Screen
import com.cng.carfelchemsafety.screens.workpermit.CreatePermitStep7Screen
import com.cng.carfelchemsafety.screens.workpermit.CreatePermitStep8Screen
import com.cng.carfelchemsafety.screens.workpermit.CreatePermitStep9Screen
import com.cng.carfelchemsafety.screens.workpermit.PermitResultScreen
import com.cng.carfelchemsafety.screens.workpermit.SubmittingPermitScreen
import com.cng.carfelchemsafety.util.Language
import com.cng.carfelchemsafety.util.Translations
import com.cng.carfelchemsafety.viewmodel.ExcelImportViewModel
import com.cng.carfelchemsafety.viewmodel.SharedLoginViewModel
import com.cng.carfelchemsafety.viewmodel.WorkPermitViewModel

@Composable
fun App() {
    val viewModel = remember { SharedLoginViewModel() }
    val permitViewModel = remember { WorkPermitViewModel() }
    val excelImportViewModel = remember { ExcelImportViewModel() }

    var currentScreen by remember { mutableStateOf(Screen.Login) }
    var termsAccepted by remember { mutableStateOf(false) }
    var currentLanguage by remember { mutableStateOf(Language.PORTUGUESE) }
    var registerFormData by remember { mutableStateOf(RegisterFormData()) }

    val loginState by viewModel.loginState.collectAsState()
    val registerState by viewModel.registerState.collectAsState()
    val passwordRecoveryState by viewModel.passwordRecoveryState.collectAsState()

    when (registerState) {
        is RegisterResult.Success -> {
            currentScreen = Screen.Login
        }
        is RegisterResult.Error -> {
            ErrorDialog((registerState as RegisterResult.Error).message)
        }
        else -> {}
    }

    when (loginState) {
        is AuthResult.Success -> {
            currentScreen = Screen.Home
        }
        is AuthResult.Error -> {
            ErrorDialog((loginState as AuthResult.Error).message)
        }
        else -> {}
    }

    MaterialTheme {
        val strings = Translations.getStrings(currentLanguage)
        val currentUser by viewModel.currentUser.collectAsState()

        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.safeDrawing),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            when (currentScreen) {
                Screen.Login -> {
                    LoginScreen(
                        strings = strings,
                        onLoginClick = { username, password ->
                            viewModel.login(username, password)
                        },
                        onForgotPasswordClick = {
                            currentScreen = Screen.ForgotPassword
                        },
                        onCreateAccountClick = {
                            termsAccepted = false
                            registerFormData = RegisterFormData()
                            currentScreen = Screen.Register
                        }
                    )
                }
                Screen.Home -> {
                    HomeScreen(
                        viewModel = viewModel,
                        strings = strings,
                        userRole = currentUser?.role ?: com.cng.carfelchemsafety.model.UserRole.COMMON,
                        onLogout = {
                            currentScreen = Screen.Login
                        },
                        onCreatePT = {
                            permitViewModel.resetForm()
                            currentScreen = Screen.CreatePermitStep1
                        },
                        onMyPermits = {
                            currentScreen = Screen.MyPermits
                        },
                        onMyApprovals = {
                            currentScreen = Screen.MyApprovals
                        },
                        onMyAccount = {
                            currentScreen = Screen.MyAccount
                        },
                        onImportExcel = {
                            currentScreen = Screen.ImportExcel
                        }
                    )
                }
                Screen.Register -> {
                    RegisterScreen(
                        strings = strings,
                        termsAccepted = termsAccepted,
                        registerState = registerState,
                        initialFormData = registerFormData,
                        onRegisterClick = { username, email, password ->
                            viewModel.register(
                                username = username,
                                email = email,
                                password = password,
                                confirmPassword = password
                            )
                        },
                        onBackClick = {
                            viewModel.resetRegisterState()
                            registerFormData = RegisterFormData()
                            termsAccepted = false
                            currentScreen = Screen.Login
                        },
                        onTermsClick = { formData ->
                            registerFormData = formData
                            currentScreen = Screen.Terms
                        },
                        onTermsCheckedChange = { checked ->
                            termsAccepted = checked
                        }
                    )
                }
                Screen.Terms -> {
                    TermsScreen(
                        strings = strings,
                        onAgreeClick = {
                            termsAccepted = true
                            currentScreen = Screen.Register
                        }
                    )
                }
                Screen.ForgotPassword -> {
                    ForgotPasswordScreen(
                        strings = strings,
                        passwordRecoveryState = passwordRecoveryState,
                        onConfirmClick = { email ->
                            viewModel.recoverPassword(email)
                        },
                        onBackClick = {
                            viewModel.resetPasswordRecoveryState()
                            currentScreen = Screen.Login
                        },
                        onSuccess = {
                            currentScreen = Screen.TempPassword
                        }
                    )
                }
                Screen.TempPassword -> {
                    val message = (passwordRecoveryState as? PasswordRecoveryResult.Success)?.message ?: ""
                    TempPasswordScreen(
                        strings = strings,
                        successMessage = message,
                        onConfirmClick = {
                            viewModel.resetPasswordRecoveryState()
                            currentScreen = Screen.Login
                        }
                    )
                }

                // Work Permit Wizard Steps
                Screen.CreatePermitStep1 -> {
                    CreatePermitStep1Screen(
                        viewModel = permitViewModel,
                        strings = strings,
                        onNext = { currentScreen = Screen.CreatePermitStep2 },
                        onCancel = {
                            permitViewModel.resetForm()
                            currentScreen = Screen.Home
                        }
                    )
                }
                Screen.CreatePermitStep2 -> {
                    CreatePermitStep2Screen(
                        viewModel = permitViewModel,
                        strings = strings,
                        onNext = { currentScreen = Screen.CreatePermitStep3 },
                        onPrevious = { currentScreen = Screen.CreatePermitStep1 }
                    )
                }
                Screen.CreatePermitStep3 -> {
                    CreatePermitStep3Screen(
                        viewModel = permitViewModel,
                        strings = strings,
                        onNext = { currentScreen = Screen.CreatePermitStep4 },
                        onPrevious = { currentScreen = Screen.CreatePermitStep2 }
                    )
                }
                Screen.CreatePermitStep4 -> {
                    CreatePermitStep4Screen(
                        viewModel = permitViewModel,
                        strings = strings,
                        onNext = { currentScreen = Screen.CreatePermitStep5 },
                        onPrevious = { currentScreen = Screen.CreatePermitStep3 }
                    )
                }
                Screen.CreatePermitStep5 -> {
                    CreatePermitStep5Screen(
                        viewModel = permitViewModel,
                        strings = strings,
                        onNext = { currentScreen = Screen.CreatePermitStep6 },
                        onPrevious = { currentScreen = Screen.CreatePermitStep4 }
                    )
                }
                Screen.CreatePermitStep6 -> {
                    CreatePermitStep6Screen(
                        viewModel = permitViewModel,
                        strings = strings,
                        onNext = { currentScreen = Screen.CreatePermitStep7 },
                        onPrevious = { currentScreen = Screen.CreatePermitStep5 }
                    )
                }
                Screen.CreatePermitStep7 -> {
                    CreatePermitStep7Screen(
                        viewModel = permitViewModel,
                        strings = strings,
                        onNext = {
                            // Conditional: skip to step 9 if height work not selected
                            if (permitViewModel.requiresHeightWork()) {
                                currentScreen = Screen.CreatePermitStep8
                            } else {
                                currentScreen = Screen.CreatePermitStep9
                            }
                        },
                        onPrevious = { currentScreen = Screen.CreatePermitStep6 }
                    )
                }
                Screen.CreatePermitStep8 -> {
                    CreatePermitStep8Screen(
                        viewModel = permitViewModel,
                        strings = strings,
                        onNext = { currentScreen = Screen.CreatePermitStep9 },
                        onPrevious = { currentScreen = Screen.CreatePermitStep7 }
                    )
                }
                Screen.CreatePermitStep9 -> {
                    CreatePermitStep9Screen(
                        viewModel = permitViewModel,
                        strings = strings,
                        createdBy = currentUser?.username ?: "",
                        onSendForApproval = {
                            currentScreen = Screen.SubmittingPermit
                        },
                        onPrevious = {
                            if (permitViewModel.requiresHeightWork()) {
                                currentScreen = Screen.CreatePermitStep8
                            } else {
                                currentScreen = Screen.CreatePermitStep7
                            }
                        }
                    )
                }

                // Post-submit flow
                Screen.SubmittingPermit -> {
                    SubmittingPermitScreen(
                        strings = strings
                    )
                    // Auto-navigate to result screen when submit completes
                    val submitResult by permitViewModel.submitResult.collectAsState()
                    if (submitResult is com.cng.carfelchemsafety.auth.WorkPermitResult.Success ||
                        submitResult is com.cng.carfelchemsafety.auth.WorkPermitResult.Error
                    ) {
                        currentScreen = Screen.PermitResult
                    }
                }
                Screen.PermitResult -> {
                    PermitResultScreen(
                        viewModel = permitViewModel,
                        strings = strings,
                        onBackToHome = {
                            currentScreen = Screen.Home
                        }
                    )
                }

                // Stub Screens
                Screen.MyPermits -> {
                    MyPermitsScreen(
                        strings = strings,
                        onBack = { currentScreen = Screen.Home }
                    )
                }
                Screen.MyApprovals -> {
                    MyApprovalsScreen(
                        strings = strings,
                        onBack = { currentScreen = Screen.Home }
                    )
                }
                Screen.MyAccount -> {
                    val profileUpdateState by viewModel.profileUpdateState.collectAsState()
                    val passwordChangeState by viewModel.passwordChangeState.collectAsState()

                    MyAccountScreen(
                        currentUser = currentUser,
                        profileUpdateState = profileUpdateState,
                        passwordChangeState = passwordChangeState,
                        strings = strings,
                        onSaveClick = { username, email ->
                            viewModel.updateProfile(username, email)
                        },
                        onChangePasswordClick = { currentPw, newPw, confirmPw ->
                            viewModel.changePassword(currentPw, newPw, confirmPw)
                        },
                        onLogoutClick = {
                            viewModel.logout()
                            currentScreen = Screen.Login
                        },
                        onBack = {
                            viewModel.resetProfileUpdateState()
                            viewModel.resetPasswordChangeState()
                            currentScreen = Screen.Home
                        }
                    )
                }

                Screen.ImportExcel -> {
                    ImportExcelScreen(
                        viewModel = excelImportViewModel,
                        strings = strings,
                        onBack = { currentScreen = Screen.Home }
                    )
                }
            }
        }
    }
}
