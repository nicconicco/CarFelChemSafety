package com.cng.carfelchemsafety

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import com.cng.carfelchemsafety.screens.LoginScreen
import com.cng.carfelchemsafety.screens.RegisterFormData
import com.cng.carfelchemsafety.screens.RegisterScreen
import com.cng.carfelchemsafety.screens.TempPasswordScreen
import com.cng.carfelchemsafety.screens.TermsScreen
import com.cng.carfelchemsafety.util.Language
import com.cng.carfelchemsafety.util.Translations
import com.cng.carfelchemsafety.viewmodel.SharedLoginViewModel

@Composable
fun App() {
    val viewModel = remember { SharedLoginViewModel() }

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

        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize(),
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
                        onLogout = {
                            currentScreen = Screen.Login
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
            }
        }
    }
}
