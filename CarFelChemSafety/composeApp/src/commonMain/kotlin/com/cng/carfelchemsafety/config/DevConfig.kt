package com.cng.carfelchemsafety.config

object DevConfig {
    /**
     * Quando true, o código de segurança não é exigido no login e registo.
     * Alterar para false antes de lançar em produção.
     */
    const val SKIP_ACCESS_CODE = true

    /**
     * Quando true, o usuario padrao local ("admin") recebe role MANAGER.
     * Alterar para false antes de lançar em produção.
     */
    const val DEFAULT_USER_ROLE_MANAGER = true
}
