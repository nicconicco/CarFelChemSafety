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
    val logoutButton: String,

    // Home Menu
    val homeGreeting: String,
    val homeHowCanWeHelp: String,
    val menuCreatePT: String,
    val menuMyPTs: String,
    val menuMyApprovals: String,
    val menuMyAccount: String,

    // Work Permit Wizard - General
    val stepOf: String,
    val next: String,
    val previous: String,
    val submit: String,
    val cancel: String,

    // Step 1 - Basic Info
    val step1Title: String,
    val workDescription: String,
    val environmentalCategory: String,
    val categoryDocumentos: String,
    val categoryEletrica: String,
    val subcategories: String,
    val subcatDocumentos: String,
    val subcatEletrica: String,
    val subcatMecanica: String,
    val subcatHidraulica: String,
    val subcatCivil: String,

    // Step 2 - Document Info
    val step2Title: String,
    val documentDetails: String,
    val supervisionExecution: String,
    val itemsToCheck: String,
    val checkItem1: String,
    val checkItem2: String,
    val checkItem3: String,
    val checkItem4: String,
    val checkItem5: String,

    // Step 3 - Exposure/Risks
    val step3Title: String,
    val exposureOptions: String,
    val exposureRuido: String,
    val exposurePoeira: String,
    val exposureQuimico: String,
    val exposureBiologico: String,
    val exposureRadiacao: String,
    val exposureVibracao: String,
    val exposureCalor: String,
    val exposureFrio: String,
    val eliminationOptions: String,
    val eliminationCorte: String,
    val eliminationChoque: String,
    val eliminationCurtoCircuito: String,
    val eliminationArcoEletrico: String,
    val eliminationBloqueio: String,
    val eliminationSinalizacao: String,

    // Step 4 - Photo
    val step4Title: String,
    val takePhoto: String,
    val photoPreview: String,
    val noPhotoTaken: String,

    // Step 5 - General Requirements
    val step5Title: String,
    val requirementsChecklist: String,
    val req1: String,
    val req2: String,
    val req3: String,
    val req4: String,
    val req5: String,
    val req6: String,
    val observationsLabel: String,

    // Step 6 - Responsible Party
    val step6Title: String,
    val responsibleName: String,
    val responsibleRole: String,
    val healthInfo: String,

    // Step 7 - Associated Works
    val step7Title: String,
    val associatedWorks: String,
    val heightWork: String,
    val hotWork: String,
    val atLtVr: String,
    val noneOption: String,

    // Step 8 - Height Work Details
    val step8Title: String,
    val heightActivities: String,
    val anchorageInfo: String,

    // Step 9 - Review & Submit
    val step9Title: String,
    val reviewSummary: String,
    val areaResponsible: String,
    val submitPermit: String,
    val permitCreatedSuccess: String,
    val orderSummaryLabel: String,
    val viewOrderSummary: String,
    val approverLabel: String,
    val singleSignature: String,
    val sendForApproval: String,

    // Submitting Screen
    val submittingTitle: String,
    val submittingMessage: String,

    // Result Screen
    val resultApproved: String,
    val resultError: String,
    val backToHome: String,

    // Stub Screens
    val myPermitsTitle: String,
    val myApprovalsTitle: String,
    val myAccountTitle: String,

    // My Account / Profile
    val myProfile: String,
    val personalInfo: String,
    val saveProfile: String,
    val profileUpdatedSuccess: String,
    val changePasswordTitle: String,
    val currentPasswordLabel: String,
    val newPasswordLabel: String,
    val confirmNewPasswordLabel: String,
    val changePasswordButton: String,
    val passwordChangedSuccess: String,
    val accountIdLabel: String,
    val logoutAccount: String,
    val restrictedAccess: String,
    val enterPasswordToContinue: String,
    val incorrectPassword: String,
    val enterButton: String,

    // Excel
    val menuImportExcel: String,
    val importExcelTitle: String,
    val importExcelSubtitle: String,
    val importEmployeesTitle: String,
    val importEmployeesDesc: String,
    val importPTDataTitle: String,
    val importPTDataDesc: String,
    val importEmployeesSuccess: String,
    val importSuccessTitle: String,
    val importPTDataSuccess: String,
    val importErrorTitle: String,
    val importButton: String
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
        logoutButton = "Sair",

        // Home Menu
        homeGreeting = "Bom dia",
        homeHowCanWeHelp = "como podemos te ajudar?",
        menuCreatePT = "Criar PT",
        menuMyPTs = "Minhas PTs",
        menuMyApprovals = "Minhas Aprovacoes",
        menuMyAccount = "Minha Conta",

        // Wizard General
        stepOf = "Passo %d de %d",
        next = "Proximo",
        previous = "Anterior",
        submit = "Enviar",
        cancel = "Cancelar",

        // Step 1
        step1Title = "Informacoes Basicas",
        workDescription = "Descricao do trabalho",
        environmentalCategory = "Categoria ambiental",
        categoryDocumentos = "Documentos",
        categoryEletrica = "Eletrica",
        subcategories = "Subcategorias",
        subcatDocumentos = "Documentos",
        subcatEletrica = "Eletrica",
        subcatMecanica = "Mecanica",
        subcatHidraulica = "Hidraulica",
        subcatCivil = "Civil",

        // Step 2
        step2Title = "Informacoes do Documento",
        documentDetails = "Detalhes do documento",
        supervisionExecution = "Supervisao de execucao",
        itemsToCheck = "Itens para verificar",
        checkItem1 = "Area isolada e sinalizada",
        checkItem2 = "Equipamentos de protecao disponiveis",
        checkItem3 = "Equipe treinada e orientada",
        checkItem4 = "Ferramentas inspecionadas",
        checkItem5 = "Comunicacao estabelecida",

        // Step 3
        step3Title = "Exposicao e Riscos",
        exposureOptions = "Opcoes de exposicao",
        exposureRuido = "Ruido",
        exposurePoeira = "Poeira",
        exposureQuimico = "Quimico",
        exposureBiologico = "Biologico",
        exposureRadiacao = "Radiacao",
        exposureVibracao = "Vibracao",
        exposureCalor = "Calor",
        exposureFrio = "Frio",
        eliminationOptions = "Opcoes de eliminacao",
        eliminationCorte = "Corte",
        eliminationChoque = "Choque",
        eliminationCurtoCircuito = "Curto-circuito",
        eliminationArcoEletrico = "Arco eletrico",
        eliminationBloqueio = "Bloqueio",
        eliminationSinalizacao = "Sinalizacao",

        // Step 4
        step4Title = "Captura de Foto",
        takePhoto = "Tirar Foto",
        photoPreview = "Pre-visualizacao",
        noPhotoTaken = "Nenhuma foto capturada",

        // Step 5
        step5Title = "Requisitos Gerais",
        requirementsChecklist = "Lista de requisitos",
        req1 = "EPI adequado disponivel",
        req2 = "Area limpa e organizada",
        req3 = "Ventilacao adequada",
        req4 = "Iluminacao adequada",
        req5 = "Extintores disponiveis",
        req6 = "Primeiros socorros acessiveis",
        observationsLabel = "Observacoes",

        // Step 6
        step6Title = "Responsavel",
        responsibleName = "Nome do responsavel",
        responsibleRole = "Funcao",
        healthInfo = "Informacoes de saude",

        // Step 7
        step7Title = "Trabalhos Associados",
        associatedWorks = "Selecione o trabalho associado",
        heightWork = "Trabalho em altura",
        hotWork = "Trabalho a quente",
        atLtVr = "AT / LT / VR",
        noneOption = "Nenhum",

        // Step 8
        step8Title = "Detalhes do Trabalho em Altura",
        heightActivities = "Atividades em altura",
        anchorageInfo = "Informacoes de ancoragem",

        // Step 9
        step9Title = "Revisao e Envio",
        reviewSummary = "Resumo da PT",
        areaResponsible = "Responsavel pela area",
        submitPermit = "Enviar PT",
        permitCreatedSuccess = "PT criada com sucesso!",
        orderSummaryLabel = "Ordem ashamada",
        viewOrderSummary = "Ver resumo da minha ordem",
        approverLabel = "Aprovador",
        singleSignature = "Unica assinatura",
        sendForApproval = "Enviar para aprovacao",

        // Submitting Screen
        submittingTitle = "Enviando",
        submittingMessage = "Aguarde enquanto transferimos sua solicitacao",

        // Result Screen
        resultApproved = "Solicitacao aprovada com sucesso!",
        resultError = "Solicitacao aprovada ou deu algum problema. Verifique suas PTs.",
        backToHome = "Voltar ao menu",

        // Stub Screens
        myPermitsTitle = "Minhas PTs",
        myApprovalsTitle = "Minhas Aprovacoes",
        myAccountTitle = "Minha Conta",

        // My Account / Profile
        myProfile = "Meu Perfil",
        personalInfo = "Informacoes pessoais",
        saveProfile = "Salvar perfil",
        profileUpdatedSuccess = "Perfil atualizado com sucesso!",
        changePasswordTitle = "Alterar senha",
        currentPasswordLabel = "Senha atual",
        newPasswordLabel = "Nova senha",
        confirmNewPasswordLabel = "Confirmar nova senha",
        changePasswordButton = "Alterar senha",
        passwordChangedSuccess = "Senha alterada com sucesso!",
        accountIdLabel = "ID da conta",
        logoutAccount = "Sair da conta",
        restrictedAccess = "Acesso Restrito",
        enterPasswordToContinue = "Digite a senha para continuar:",
        incorrectPassword = "Senha incorreta",
        enterButton = "Entrar",
        menuImportExcel = "Importar Excel",
        importExcelTitle = "Importar Excel",
        importExcelSubtitle = "Selecione o tipo de dados para importar",
        importEmployeesTitle = "Importar Funcionarios",
        importEmployeesDesc = "Importe a lista de funcionarios a partir de uma planilha Excel.",
        importPTDataTitle = "Importar Dados de PTs",
        importPTDataDesc = "Importe dados de permissoes de trabalho a partir de uma planilha Excel.",
        importEmployeesSuccess = "%d funcionarios importados com sucesso!",
        importSuccessTitle = "Importacao Concluida",
        importPTDataSuccess = "%d PTs importadas com sucesso!",
        importErrorTitle = "Erro na Importacao",
        importButton = "Importar"
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
        logoutButton = "Logout",

        // Home Menu
        homeGreeting = "Good morning",
        homeHowCanWeHelp = "how can we help you?",
        menuCreatePT = "Create PT",
        menuMyPTs = "My PTs",
        menuMyApprovals = "My Approvals",
        menuMyAccount = "My Account",

        // Wizard General
        stepOf = "Step %d of %d",
        next = "Next",
        previous = "Previous",
        submit = "Submit",
        cancel = "Cancel",

        // Step 1
        step1Title = "Basic Information",
        workDescription = "Work description",
        environmentalCategory = "Environmental category",
        categoryDocumentos = "Documents",
        categoryEletrica = "Electrical",
        subcategories = "Subcategories",
        subcatDocumentos = "Documents",
        subcatEletrica = "Electrical",
        subcatMecanica = "Mechanical",
        subcatHidraulica = "Hydraulic",
        subcatCivil = "Civil",

        // Step 2
        step2Title = "Document Information",
        documentDetails = "Document details",
        supervisionExecution = "Supervision execution",
        itemsToCheck = "Items to check",
        checkItem1 = "Area isolated and signaled",
        checkItem2 = "Protective equipment available",
        checkItem3 = "Team trained and oriented",
        checkItem4 = "Tools inspected",
        checkItem5 = "Communication established",

        // Step 3
        step3Title = "Exposure and Risks",
        exposureOptions = "Exposure options",
        exposureRuido = "Noise",
        exposurePoeira = "Dust",
        exposureQuimico = "Chemical",
        exposureBiologico = "Biological",
        exposureRadiacao = "Radiation",
        exposureVibracao = "Vibration",
        exposureCalor = "Heat",
        exposureFrio = "Cold",
        eliminationOptions = "Elimination options",
        eliminationCorte = "Cut",
        eliminationChoque = "Shock",
        eliminationCurtoCircuito = "Short circuit",
        eliminationArcoEletrico = "Electric arc",
        eliminationBloqueio = "Lockout",
        eliminationSinalizacao = "Signaling",

        // Step 4
        step4Title = "Photo Capture",
        takePhoto = "Take Photo",
        photoPreview = "Preview",
        noPhotoTaken = "No photo taken",

        // Step 5
        step5Title = "General Requirements",
        requirementsChecklist = "Requirements checklist",
        req1 = "Adequate PPE available",
        req2 = "Area clean and organized",
        req3 = "Adequate ventilation",
        req4 = "Adequate lighting",
        req5 = "Fire extinguishers available",
        req6 = "First aid accessible",
        observationsLabel = "Observations",

        // Step 6
        step6Title = "Responsible Party",
        responsibleName = "Responsible name",
        responsibleRole = "Role",
        healthInfo = "Health information",

        // Step 7
        step7Title = "Associated Works",
        associatedWorks = "Select associated work",
        heightWork = "Height work",
        hotWork = "Hot work",
        atLtVr = "AT / LT / VR",
        noneOption = "None",

        // Step 8
        step8Title = "Height Work Details",
        heightActivities = "Height activities",
        anchorageInfo = "Anchorage information",

        // Step 9
        step9Title = "Review & Submit",
        reviewSummary = "PT Summary",
        areaResponsible = "Area responsible",
        submitPermit = "Submit PT",
        permitCreatedSuccess = "PT created successfully!",
        orderSummaryLabel = "Order summary",
        viewOrderSummary = "View my order summary",
        approverLabel = "Approver",
        singleSignature = "Single signature",
        sendForApproval = "Send for approval",

        // Submitting Screen
        submittingTitle = "Submitting",
        submittingMessage = "Please wait while we transfer your request",

        // Result Screen
        resultApproved = "Request approved successfully!",
        resultError = "Request approved or something went wrong. Check your PTs.",
        backToHome = "Back to menu",

        // Stub Screens
        myPermitsTitle = "My PTs",
        myApprovalsTitle = "My Approvals",
        myAccountTitle = "My Account",

        // My Account / Profile
        myProfile = "My Profile",
        personalInfo = "Personal information",
        saveProfile = "Save profile",
        profileUpdatedSuccess = "Profile updated successfully!",
        changePasswordTitle = "Change password",
        currentPasswordLabel = "Current password",
        newPasswordLabel = "New password",
        confirmNewPasswordLabel = "Confirm new password",
        changePasswordButton = "Change password",
        passwordChangedSuccess = "Password changed successfully!",
        accountIdLabel = "Account ID",
        logoutAccount = "Log out",
        restrictedAccess = "Restricted Access",
        enterPasswordToContinue = "Enter password to continue:",
        incorrectPassword = "Incorrect password",
        enterButton = "Enter",
        menuImportExcel = "Import Excel",
        importExcelTitle = "Import Excel",
        importExcelSubtitle = "Select the type of data to import",
        importEmployeesTitle = "Import Employees",
        importEmployeesDesc = "Import the employee list from an Excel spreadsheet.",
        importPTDataTitle = "Import PT Data",
        importPTDataDesc = "Import work permit data from an Excel spreadsheet.",
        importEmployeesSuccess = "%d employees imported successfully!",
        importSuccessTitle = "Import Complete",
        importPTDataSuccess = "%d PTs imported successfully!",
        importErrorTitle = "Import Error",
        importButton = "Import"
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
        logoutButton = "Salir",

        // Home Menu
        homeGreeting = "Buenos dias",
        homeHowCanWeHelp = "como podemos ayudarte?",
        menuCreatePT = "Crear PT",
        menuMyPTs = "Mis PTs",
        menuMyApprovals = "Mis Aprobaciones",
        menuMyAccount = "Mi Cuenta",

        // Wizard General
        stepOf = "Paso %d de %d",
        next = "Siguiente",
        previous = "Anterior",
        submit = "Enviar",
        cancel = "Cancelar",

        // Step 1
        step1Title = "Informacion Basica",
        workDescription = "Descripcion del trabajo",
        environmentalCategory = "Categoria ambiental",
        categoryDocumentos = "Documentos",
        categoryEletrica = "Electrica",
        subcategories = "Subcategorias",
        subcatDocumentos = "Documentos",
        subcatEletrica = "Electrica",
        subcatMecanica = "Mecanica",
        subcatHidraulica = "Hidraulica",
        subcatCivil = "Civil",

        // Step 2
        step2Title = "Informacion del Documento",
        documentDetails = "Detalles del documento",
        supervisionExecution = "Supervision de ejecucion",
        itemsToCheck = "Items a verificar",
        checkItem1 = "Area aislada y senalizada",
        checkItem2 = "Equipos de proteccion disponibles",
        checkItem3 = "Equipo entrenado y orientado",
        checkItem4 = "Herramientas inspeccionadas",
        checkItem5 = "Comunicacion establecida",

        // Step 3
        step3Title = "Exposicion y Riesgos",
        exposureOptions = "Opciones de exposicion",
        exposureRuido = "Ruido",
        exposurePoeira = "Polvo",
        exposureQuimico = "Quimico",
        exposureBiologico = "Biologico",
        exposureRadiacao = "Radiacion",
        exposureVibracao = "Vibracion",
        exposureCalor = "Calor",
        exposureFrio = "Frio",
        eliminationOptions = "Opciones de eliminacion",
        eliminationCorte = "Corte",
        eliminationChoque = "Choque",
        eliminationCurtoCircuito = "Cortocircuito",
        eliminationArcoEletrico = "Arco electrico",
        eliminationBloqueio = "Bloqueo",
        eliminationSinalizacao = "Senalizacion",

        // Step 4
        step4Title = "Captura de Foto",
        takePhoto = "Tomar Foto",
        photoPreview = "Vista previa",
        noPhotoTaken = "Ninguna foto tomada",

        // Step 5
        step5Title = "Requisitos Generales",
        requirementsChecklist = "Lista de requisitos",
        req1 = "EPP adecuado disponible",
        req2 = "Area limpia y organizada",
        req3 = "Ventilacion adecuada",
        req4 = "Iluminacion adecuada",
        req5 = "Extintores disponibles",
        req6 = "Primeros auxilios accesibles",
        observationsLabel = "Observaciones",

        // Step 6
        step6Title = "Responsable",
        responsibleName = "Nombre del responsable",
        responsibleRole = "Funcion",
        healthInfo = "Informacion de salud",

        // Step 7
        step7Title = "Trabajos Asociados",
        associatedWorks = "Seleccione el trabajo asociado",
        heightWork = "Trabajo en altura",
        hotWork = "Trabajo en caliente",
        atLtVr = "AT / LT / VR",
        noneOption = "Ninguno",

        // Step 8
        step8Title = "Detalles del Trabajo en Altura",
        heightActivities = "Actividades en altura",
        anchorageInfo = "Informacion de anclaje",

        // Step 9
        step9Title = "Revision y Envio",
        reviewSummary = "Resumen de la PT",
        areaResponsible = "Responsable del area",
        submitPermit = "Enviar PT",
        permitCreatedSuccess = "PT creada con exito!",
        orderSummaryLabel = "Resumen del orden",
        viewOrderSummary = "Ver resumen de mi orden",
        approverLabel = "Aprobador",
        singleSignature = "Firma unica",
        sendForApproval = "Enviar para aprobacion",

        // Submitting Screen
        submittingTitle = "Enviando",
        submittingMessage = "Espere mientras transferimos su solicitud",

        // Result Screen
        resultApproved = "Solicitud aprobada con exito!",
        resultError = "Solicitud aprobada o hubo algun problema. Verifique sus PTs.",
        backToHome = "Volver al menu",

        // Stub Screens
        myPermitsTitle = "Mis PTs",
        myApprovalsTitle = "Mis Aprobaciones",
        myAccountTitle = "Mi Cuenta",

        // My Account / Profile
        myProfile = "Mi Perfil",
        personalInfo = "Informacion personal",
        saveProfile = "Guardar perfil",
        profileUpdatedSuccess = "Perfil actualizado con exito!",
        changePasswordTitle = "Cambiar contrasena",
        currentPasswordLabel = "Contrasena actual",
        newPasswordLabel = "Nueva contrasena",
        confirmNewPasswordLabel = "Confirmar nueva contrasena",
        changePasswordButton = "Cambiar contrasena",
        passwordChangedSuccess = "Contrasena cambiada con exito!",
        accountIdLabel = "ID de la cuenta",
        logoutAccount = "Cerrar sesion",
        restrictedAccess = "Acceso Restringido",
        enterPasswordToContinue = "Ingrese la contrasena para continuar:",
        incorrectPassword = "Contrasena incorrecta",
        enterButton = "Entrar",
        menuImportExcel = "Importar Excel",
        importExcelTitle = "Importar Excel",
        importExcelSubtitle = "Seleccione el tipo de datos a importar",
        importEmployeesTitle = "Importar Empleados",
        importEmployeesDesc = "Importe la lista de empleados desde una hoja de calculo Excel.",
        importPTDataTitle = "Importar Datos de PTs",
        importPTDataDesc = "Importe datos de permisos de trabajo desde una hoja de calculo Excel.",
        importEmployeesSuccess = "%d empleados importados con exito!",
        importSuccessTitle = "Importacion Completa",
        importPTDataSuccess = "%d PTs importadas con exito!",
        importErrorTitle = "Error en la Importacion",
        importButton = "Importar"
    )

    fun getStrings(language: Language): AppStrings {
        return when (language) {
            Language.PORTUGUESE -> portuguese
            Language.ENGLISH -> english
            Language.SPANISH -> spanish
        }
    }
}
