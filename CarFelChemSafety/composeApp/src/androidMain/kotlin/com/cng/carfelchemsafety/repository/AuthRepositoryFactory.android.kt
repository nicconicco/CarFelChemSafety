package com.cng.carfelchemsafety.repository

actual fun createAuthRepository(): AuthRepository = FirebaseAuthRepository()
