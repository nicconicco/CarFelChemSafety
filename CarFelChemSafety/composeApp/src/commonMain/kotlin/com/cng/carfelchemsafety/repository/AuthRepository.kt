package com.cng.carfelchemsafety.repository

import com.cng.carfelchemsafety.model.User

interface AuthRepository {
    suspend fun login(username: String, password: String): Result<User>
    suspend fun register(username: String, email: String, password: String, isGestor: Boolean): Result<User>
    suspend fun recoverPassword(email: String): Result<String>
    suspend fun getUserByEmail(email: String): User?
    suspend fun getUserByUsername(username: String): User?
    suspend fun updateProfile(userId: String, username: String, email: String): Result<User>
    suspend fun changePassword(currentPassword: String, newPassword: String): Result<String>
}
