package com.cng.carfelchemsafety.repository

import com.cng.carfelchemsafety.model.WorkPermit

class LocalWorkPermitRepository : WorkPermitRepository {

    private val permits = mutableListOf<WorkPermit>()

    override suspend fun createPermit(permit: WorkPermit): Result<WorkPermit> {
        return try {
            val newPermit = permit.copy(
                id = generateId(),
                createdAt = currentTimeMillis()
            )
            permits.add(newPermit)
            Result.success(newPermit)
        } catch (e: Exception) {
            Result.failure(Exception("Erro ao criar PT: ${e.message}"))
        }
    }

    override suspend fun getPermits(): Result<List<WorkPermit>> {
        return Result.success(permits.toList())
    }

    override suspend fun getPermitById(id: String): Result<WorkPermit?> {
        return Result.success(permits.find { it.id == id })
    }

    private fun generateId(): String {
        return "PT-${permits.size + 1}-${currentTimeMillis()}"
    }

    private fun currentTimeMillis(): Long {
        return kotlin.random.Random.nextLong(1000000000L, 9999999999L)
    }
}
