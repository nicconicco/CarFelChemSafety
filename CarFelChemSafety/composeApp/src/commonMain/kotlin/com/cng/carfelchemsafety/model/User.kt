package com.cng.carfelchemsafety.model

data class User(
    val id: String,
    val username: String,
    val email: String,
    val passwordHash: String
)
