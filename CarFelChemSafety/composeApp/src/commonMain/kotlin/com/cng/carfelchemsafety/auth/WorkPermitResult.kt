package com.cng.carfelchemsafety.auth

import com.cng.carfelchemsafety.model.WorkPermit

sealed class WorkPermitResult {
    data object Idle : WorkPermitResult()
    data object Loading : WorkPermitResult()
    data class Success(val permit: WorkPermit) : WorkPermitResult()
    data class Error(val message: String) : WorkPermitResult()
}
