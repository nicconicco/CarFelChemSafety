package com.cng.carfelchemsafety.util

enum class Language(val code: String, val flag: String) {
    PORTUGUESE("pt-BR", "\uD83C\uDDE7\uD83C\uDDF7"),
    ENGLISH("en-US", "\uD83C\uDDFA\uD83C\uDDF8"),
    SPANISH("es-ES", "\uD83C\uDDEA\uD83C\uDDF8")
}

data class AppStrings(
    // Login
    val appName: String,
    val username: String,
    val password: String,
    val forgotPassword: String,
    val signIn: String,
    val createAccount: String,

    // Register
    val register: String,
    val name: String,
    val email: String,
    val cpf: String,
    val phone: String,
    val confirmPassword: String,
    val requiredFields: String,
    val acceptTerms: String,
    val registerButton: String,
    val backToLogin: String,

    // Terms
    val termsTitle: String,
    val termsText: String,
    val agree: String,

    // Forgot Password
    val forgotPasswordTitle: String,
    val confirm: String,
    val back: String,

    // Temp Password
    val tempPasswordTitle: String,
    val yourNewPassword: String,
    val useTempPassword: String,

    // Access Code
    val accessCode: String,
    val invalidAccessCode: String,

    // Language
    val selectLanguage: String,
    val portuguese: String,
    val english: String,
    val spanish: String,

    // Home
    val welcome: String,
    val logoutButton: String
)

object Translations {
    val portuguese = AppStrings(
        appName = "CarFelChemSafety",
        username = "Usuario",
        password = "Senha",
        forgotPassword = "Esqueci a senha",
        signIn = "Entrar",
        createAccount = "Criar nova conta",

        register = "Criar Conta",
        name = "Nome",
        email = "Email",
        cpf = "CPF",
        phone = "Celular",
        confirmPassword = "Confirmar senha",
        requiredFields = "* Campos obrigatorios",
        acceptTerms = "Aceito os termos de compromisso",
        registerButton = "Cadastrar",
        backToLogin = "Voltar ao login",

        termsTitle = "Termos de Compromisso",
        termsText = "Ao utilizar este aplicativo, voce concorda que o CarFelChemSafety podera coletar suas informacoes pessoais para melhorar sua experiencia e fornecer nossos servicos.\n\nSuas informacoes serao tratadas com seguranca e nao serao compartilhadas com terceiros sem seu consentimento.\n\nAo clicar em \"Concordar\", voce declara estar de acordo com estes termos.",
        agree = "Concordar",

        forgotPasswordTitle = "Esqueci a Senha",
        confirm = "Confirmar",
        back = "Voltar",

        tempPasswordTitle = "Senha Temporaria",
        yourNewPassword = "Sua nova senha e:",
        useTempPassword = "Use esta senha para fazer login e depois altere para uma senha de sua preferencia.",

        accessCode = "Codigo de acesso",
        invalidAccessCode = "Codigo de acesso invalido",

        selectLanguage = "Selecione o Idioma",
        portuguese = "Portugues (Brasil)",
        english = "English (US)",
        spanish = "Espanol",

        welcome = "Bem-vindo",
        logoutButton = "Sair"
    )

    val english = AppStrings(
        appName = "CarFelChemSafety",
        username = "Username",
        password = "Password",
        forgotPassword = "Forgot password",
        signIn = "Sign In",
        createAccount = "Create new account",

        register = "Create Account",
        name = "Name",
        email = "Email",
        cpf = "CPF",
        phone = "Phone",
        confirmPassword = "Confirm password",
        requiredFields = "* Required fields",
        acceptTerms = "I accept the terms and conditions",
        registerButton = "Register",
        backToLogin = "Back to login",

        termsTitle = "Terms and Conditions",
        termsText = "By using this application, you agree that CarFelChemSafety may collect your personal information to improve your experience and provide our services.\n\nYour information will be treated securely and will not be shared with third parties without your consent.\n\nBy clicking \"Agree\", you declare that you agree to these terms.",
        agree = "Agree",

        forgotPasswordTitle = "Forgot Password",
        confirm = "Confirm",
        back = "Back",

        tempPasswordTitle = "Temporary Password",
        yourNewPassword = "Your new password is:",
        useTempPassword = "Use this password to log in and then change it to a password of your choice.",

        accessCode = "Access code",
        invalidAccessCode = "Invalid access code",

        selectLanguage = "Select Language",
        portuguese = "Portugues (Brasil)",
        english = "English (US)",
        spanish = "Espanol",

        welcome = "Welcome",
        logoutButton = "Logout"
    )

    val spanish = AppStrings(
        appName = "CarFelChemSafety",
        username = "Usuario",
        password = "Contrasena",
        forgotPassword = "Olvide mi contrasena",
        signIn = "Iniciar sesion",
        createAccount = "Crear nueva cuenta",

        register = "Crear Cuenta",
        name = "Nombre",
        email = "Correo electronico",
        cpf = "CPF",
        phone = "Telefono",
        confirmPassword = "Confirmar contrasena",
        requiredFields = "* Campos obligatorios",
        acceptTerms = "Acepto los terminos y condiciones",
        registerButton = "Registrarse",
        backToLogin = "Volver al inicio",

        termsTitle = "Terminos y Condiciones",
        termsText = "Al utilizar esta aplicacion, usted acepta que CarFelChemSafety pueda recopilar su informacion personal para mejorar su experiencia y proporcionar nuestros servicios.\n\nSu informacion sera tratada de forma segura y no sera compartida con terceros sin su consentimiento.\n\nAl hacer clic en \"Aceptar\", declara estar de acuerdo con estos terminos.",
        agree = "Aceptar",

        forgotPasswordTitle = "Olvide mi Contrasena",
        confirm = "Confirmar",
        back = "Volver",

        tempPasswordTitle = "Contrasena Temporal",
        yourNewPassword = "Su nueva contrasena es:",
        useTempPassword = "Use esta contrasena para iniciar sesion y luego cambiela por una contrasena de su preferencia.",

        accessCode = "Codigo de acceso",
        invalidAccessCode = "Codigo de acceso invalido",

        selectLanguage = "Seleccionar Idioma",
        portuguese = "Portugues (Brasil)",
        english = "English (US)",
        spanish = "Espanol",

        welcome = "Bienvenido",
        logoutButton = "Salir"
    )

    fun getStrings(language: Language): AppStrings {
        return when (language) {
            Language.PORTUGUESE -> portuguese
            Language.ENGLISH -> english
            Language.SPANISH -> spanish
        }
    }
}
