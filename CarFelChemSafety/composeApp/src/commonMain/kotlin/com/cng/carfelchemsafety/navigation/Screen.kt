package com.cng.carfelchemsafety.navigation

enum class Screen {
    Login,
    Register,
    Terms,
    ForgotPassword,
    TempPassword,
    Home,

    // Work Permit Wizard Steps
    CreatePermitStep1,
    CreatePermitStep2,
    CreatePermitStep3,
    CreatePermitStep4,
    CreatePermitStep5,
    CreatePermitStep6,
    CreatePermitStep7,
    CreatePermitStep8,
    CreatePermitStep9,

    // Post-submit flow
    SubmittingPermit,
    PermitResult,

    // Stub screens
    MyPermits,
    MyApprovals,
    MyAccount,

    // Excel Import
    ImportExcel
}
