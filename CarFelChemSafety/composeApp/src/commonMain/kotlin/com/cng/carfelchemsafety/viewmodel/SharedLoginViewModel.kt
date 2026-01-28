package com.cng.carfelchemsafety.viewmodel

import com.cng.carfelchemsafety.auth.AuthResult
import com.cng.carfelchemsafety.auth.PasswordChangeResult
import com.cng.carfelchemsafety.auth.PasswordRecoveryResult
import com.cng.carfelchemsafety.auth.ProfileUpdateResult
import com.cng.carfelchemsafety.auth.RegisterResult
import com.cng.carfelchemsafety.model.User
import com.cng.carfelchemsafety.repository.AuthRepository
import com.cng.carfelchemsafety.repository.createAuthRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class SharedLoginViewModel(
    private val repository: AuthRepository = createAuthRepository()
) {
    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    private val _loginState = MutableStateFlow<AuthResult>(AuthResult.Idle)
    val loginState: StateFlow<AuthResult> = _loginState.asStateFlow()

    private val _registerState = MutableStateFlow<RegisterResult>(RegisterResult.Idle)
    val registerState: StateFlow<RegisterResult> = _registerState.asStateFlow()

    private val _passwordRecoveryState = MutableStateFlow<PasswordRecoveryResult>(PasswordRecoveryResult.Idle)
    val passwordRecoveryState: StateFlow<PasswordRecoveryResult> = _passwordRecoveryState.asStateFlow()

    private val _profileUpdateState = MutableStateFlow<ProfileUpdateResult>(ProfileUpdateResult.Idle)
    val profileUpdateState: StateFlow<ProfileUpdateResult> = _profileUpdateState.asStateFlow()

    private val _passwordChangeState = MutableStateFlow<PasswordChangeResult>(PasswordChangeResult.Idle)
    val passwordChangeState: StateFlow<PasswordChangeResult> = _passwordChangeState.asStateFlow()

    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> = _currentUser.asStateFlow()

    fun login(username: String, password: String) {
        _loginState.value = AuthResult.Idle

        if (username.isBlank() || password.isBlank()) {
            _loginState.value = AuthResult.Error("Preencha todos os campos")
            return
        }

        viewModelScope.launch {
            _loginState.value = AuthResult.Loading

            repository.login(username, password)
                .onSuccess { user ->
                    _currentUser.value = user
                    _loginState.value = AuthResult.Success(user)
                }
                .onFailure { exception ->
                    _loginState.value = AuthResult.Error(exception.message ?: "Erro ao fazer login")
                }
        }
    }

    fun register(
        username: String,
        email: String,
        password: String,
        confirmPassword: String,
        isGestor: Boolean
    ) {
        _registerState.value = RegisterResult.Idle

        when {
            username.isBlank() || email.isBlank() || password.isBlank() -> {
                _registerState.value = RegisterResult.Error("Preencha todos os campos")
                return
            }
            password != confirmPassword -> {
                _registerState.value = RegisterResult.Error("As senhas nao coincidem")
                return
            }
        }

        viewModelScope.launch {
            _registerState.value = RegisterResult.Loading

            repository.register(username, email, password, isGestor)
                .onSuccess {
                    _registerState.value = RegisterResult.Success
                }
                .onFailure { exception ->
                    _registerState.value = RegisterResult.Error(exception.message ?: "Erro ao registrar")
                }
        }
    }

    fun recoverPassword(email: String) {
        _passwordRecoveryState.value = PasswordRecoveryResult.Idle

        if (email.isBlank()) {
            _passwordRecoveryState.value = PasswordRecoveryResult.Error("Informe o email")
            return
        }

        viewModelScope.launch {
            _passwordRecoveryState.value = PasswordRecoveryResult.Loading

            repository.recoverPassword(email)
                .onSuccess { message ->
                    _passwordRecoveryState.value = PasswordRecoveryResult.Success(message)
                }
                .onFailure { exception ->
                    _passwordRecoveryState.value = PasswordRecoveryResult.Error(
                        exception.message ?: "Erro ao recuperar senha"
                    )
                }
        }
    }

    fun updateProfile(username: String, email: String) {
        _profileUpdateState.value = ProfileUpdateResult.Idle

        val user = _currentUser.value
        if (user == null) {
            _profileUpdateState.value = ProfileUpdateResult.Error("Usuario nao logado")
            return
        }

        if (username.isBlank() || email.isBlank()) {
            _profileUpdateState.value = ProfileUpdateResult.Error("Preencha todos os campos")
            return
        }

        viewModelScope.launch {
            _profileUpdateState.value = ProfileUpdateResult.Loading

            repository.updateProfile(user.id, username, email)
                .onSuccess { updatedUser ->
                    _currentUser.value = updatedUser
                    _profileUpdateState.value = ProfileUpdateResult.Success(updatedUser)
                }
                .onFailure { exception ->
                    _profileUpdateState.value = ProfileUpdateResult.Error(
                        exception.message ?: "Erro ao atualizar perfil"
                    )
                }
        }
    }

    fun resetProfileUpdateState() {
        _profileUpdateState.value = ProfileUpdateResult.Idle
    }

    fun changePassword(currentPassword: String, newPassword: String, confirmNewPassword: String) {
        _passwordChangeState.value = PasswordChangeResult.Idle

        if (_currentUser.value == null) {
            _passwordChangeState.value = PasswordChangeResult.Error("Usuario nao logado")
            return
        }

        if (currentPassword.isBlank() || newPassword.isBlank() || confirmNewPassword.isBlank()) {
            _passwordChangeState.value = PasswordChangeResult.Error("Preencha todos os campos")
            return
        }

        if (newPassword != confirmNewPassword) {
            _passwordChangeState.value = PasswordChangeResult.Error("As novas senhas nao coincidem")
            return
        }

        if (newPassword.length < 6) {
            _passwordChangeState.value = PasswordChangeResult.Error("Nova senha deve ter pelo menos 6 caracteres")
            return
        }

        viewModelScope.launch {
            _passwordChangeState.value = PasswordChangeResult.Loading

            repository.changePassword(currentPassword, newPassword)
                .onSuccess { message ->
                    _passwordChangeState.value = PasswordChangeResult.Success(message)
                }
                .onFailure { exception ->
                    _passwordChangeState.value = PasswordChangeResult.Error(
                        exception.message ?: "Erro ao alterar senha"
                    )
                }
        }
    }

    fun resetPasswordChangeState() {
        _passwordChangeState.value = PasswordChangeResult.Idle
    }

    fun logout() {
        _currentUser.value = null
        _loginState.value = AuthResult.Idle
        _profileUpdateState.value = ProfileUpdateResult.Idle
        _passwordChangeState.value = PasswordChangeResult.Idle
    }

    fun resetLoginState() {
        _loginState.value = AuthResult.Idle
    }

    fun resetRegisterState() {
        _registerState.value = RegisterResult.Idle
    }

    fun resetPasswordRecoveryState() {
        _passwordRecoveryState.value = PasswordRecoveryResult.Idle
    }

    fun isLoggedIn(): Boolean = _currentUser.value != null

    fun observeLoginState(callback: (AuthResult) -> Unit): Job {
        return loginState.onEach { callback(it) }.launchIn(viewModelScope)
    }

    fun observeRegisterState(callback: (RegisterResult) -> Unit): Job {
        return registerState.onEach { callback(it) }.launchIn(viewModelScope)
    }

    fun observePasswordRecoveryState(callback: (PasswordRecoveryResult) -> Unit): Job {
        return passwordRecoveryState.onEach { callback(it) }.launchIn(viewModelScope)
    }

    fun observeProfileUpdateState(callback: (ProfileUpdateResult) -> Unit): Job {
        return profileUpdateState.onEach { callback(it) }.launchIn(viewModelScope)
    }

    fun observePasswordChangeState(callback: (PasswordChangeResult) -> Unit): Job {
        return passwordChangeState.onEach { callback(it) }.launchIn(viewModelScope)
    }
}
