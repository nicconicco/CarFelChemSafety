package com.cng.carfelchemsafety.repository

import com.cng.carfelchemsafety.model.WorkPermit

interface WorkPermitRepository {
    suspend fun createPermit(permit: WorkPermit): Result<WorkPermit>
    suspend fun getPermits(): Result<List<WorkPermit>>
    suspend fun getPermitById(id: String): Result<WorkPermit?>
}
