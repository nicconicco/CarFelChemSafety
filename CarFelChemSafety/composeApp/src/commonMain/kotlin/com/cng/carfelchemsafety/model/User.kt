package com.cng.carfelchemsafety.model

enum class UserRole {
    COMMON,
    MANAGER
}

data class User(
    val id: String,
    val username: String,
    val email: String,
    val passwordHash: String,
    val role: UserRole = UserRole.COMMON
)
