package com.cng.carfelchemsafety.repository

import com.cng.carfelchemsafety.model.User
import kotlin.random.Random

class LocalAuthRepository : AuthRepository {

    private val users = mutableMapOf<String, User>()

    init {
        val defaultUser = User(
            id = "1",
            username = "admin",
            email = "admin@example.com",
            passwordHash = hashPassword("password")
        )
        users[defaultUser.username] = defaultUser
    }

    override suspend fun login(username: String, password: String): Result<User> {
        val user = users[username]

        return when {
            user == null -> Result.failure(Exception("Usuario nao encontrado"))
            user.passwordHash != hashPassword(password) -> Result.failure(Exception("Senha incorreta"))
            else -> Result.success(user)
        }
    }

    override suspend fun register(username: String, email: String, password: String): Result<User> {
        if (users.containsKey(username)) {
            return Result.failure(Exception("Nome de usuario ja existe"))
        }

        if (users.values.any { it.email == email }) {
            return Result.failure(Exception("Email ja cadastrado"))
        }

        if (username.length < 3) {
            return Result.failure(Exception("Nome de usuario deve ter pelo menos 3 caracteres"))
        }

        if (password.length < 6) {
            return Result.failure(Exception("Senha deve ter pelo menos 6 caracteres"))
        }

        if (!isValidEmail(email)) {
            return Result.failure(Exception("Email invalido"))
        }

        val newUser = User(
            id = generateId(),
            username = username,
            email = email,
            passwordHash = hashPassword(password)
        )

        users[username] = newUser
        return Result.success(newUser)
    }

    override suspend fun recoverPassword(email: String): Result<String> {
        val user = users.values.find { it.email == email }
            ?: return Result.failure(Exception("Email nao encontrado"))

        val newPassword = "123456"

        val updatedUser = user.copy(passwordHash = hashPassword(newPassword))
        users[user.username] = updatedUser

        return Result.success("Senha redefinida para: $newPassword")
    }

    override suspend fun getUserByEmail(email: String): User? {
        return users.values.find { it.email == email }
    }

    override suspend fun getUserByUsername(username: String): User? {
        return users[username]
    }

    override suspend fun updateProfile(userId: String, username: String, email: String): Result<User> {
        val existingUser = users.values.find { it.id == userId }
            ?: return Result.failure(Exception("Usuario nao encontrado"))

        if (username.length < 3) {
            return Result.failure(Exception("Nome de usuario deve ter pelo menos 3 caracteres"))
        }

        if (!isValidEmail(email)) {
            return Result.failure(Exception("Email invalido"))
        }

        if (users.containsKey(username) && users[username]?.id != userId) {
            return Result.failure(Exception("Nome de usuario ja existe"))
        }

        if (users.values.any { it.email == email && it.id != userId }) {
            return Result.failure(Exception("Email ja cadastrado"))
        }

        users.remove(existingUser.username)

        val updatedUser = existingUser.copy(
            username = username,
            email = email
        )

        users[username] = updatedUser
        return Result.success(updatedUser)
    }

    override suspend fun changePassword(currentPassword: String, newPassword: String): Result<String> {
        if (newPassword.length < 6) {
            return Result.failure(Exception("Nova senha deve ter pelo menos 6 caracteres"))
        }

        return Result.success("Senha alterada com sucesso")
    }

    private fun hashPassword(password: String): String {
        return password.hashCode().toString()
    }

    private fun generateId(): String {
        return Random.nextLong(1000000, 9999999).toString()
    }

    private fun isValidEmail(email: String): Boolean {
        return email.contains("@") && email.contains(".")
    }
}
