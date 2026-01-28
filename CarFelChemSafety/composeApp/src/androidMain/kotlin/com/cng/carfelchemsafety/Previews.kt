package com.cng.carfelchemsafety

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.cng.carfelchemsafety.model.UserRole
import com.cng.carfelchemsafety.screens.HomeScreen
import com.cng.carfelchemsafety.screens.ImportExcelScreen
import com.cng.carfelchemsafety.screens.MyAccountScreen
import com.cng.carfelchemsafety.screens.MyApprovalsScreen
import com.cng.carfelchemsafety.screens.MyPermitsScreen
import com.cng.carfelchemsafety.screens.workpermit.CreatePermitStep1Screen
import com.cng.carfelchemsafety.screens.workpermit.CreatePermitStep2Screen
import com.cng.carfelchemsafety.screens.workpermit.CreatePermitStep3Screen
import com.cng.carfelchemsafety.screens.workpermit.CreatePermitStep4Screen
import com.cng.carfelchemsafety.screens.workpermit.CreatePermitStep5Screen
import com.cng.carfelchemsafety.screens.workpermit.CreatePermitStep6Screen
import com.cng.carfelchemsafety.screens.workpermit.CreatePermitStep7Screen
import com.cng.carfelchemsafety.screens.workpermit.CreatePermitStep8Screen
import com.cng.carfelchemsafety.screens.workpermit.CreatePermitStep9Screen
import com.cng.carfelchemsafety.screens.workpermit.PermitResultScreen
import com.cng.carfelchemsafety.screens.workpermit.StepProgressIndicator
import com.cng.carfelchemsafety.screens.workpermit.SubmittingPermitScreen
import com.cng.carfelchemsafety.util.Translations
import com.cng.carfelchemsafety.viewmodel.ExcelImportViewModel
import com.cng.carfelchemsafety.viewmodel.SharedLoginViewModel
import com.cng.carfelchemsafety.viewmodel.WorkPermitViewModel

// -- Home Menu --

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    MaterialTheme {
        HomeScreen(
            viewModel = SharedLoginViewModel(),
            strings = Translations.portuguese,
            onLogout = {},
            onCreatePT = {},
            onMyPermits = {},
            onMyApprovals = {},
            onMyAccount = {},
            userRole = UserRole.MANAGER,
            onImportExcel = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenCommonPreview() {
    MaterialTheme {
        HomeScreen(
            viewModel = SharedLoginViewModel(),
            strings = Translations.portuguese,
            userRole = UserRole.COMMON,
            onLogout = {},
            onCreatePT = {},
            onMyPermits = {},
            onMyApprovals = {},
            onMyAccount = {},
            onImportExcel = {}
        )
    }
}

// -- Import Excel --

@Preview(showBackground = true)
@Composable
fun ImportExcelScreenPreview() {
    MaterialTheme {
        ImportExcelScreen(
            viewModel = ExcelImportViewModel(),
            strings = Translations.portuguese,
            onBack = {}
        )
    }
}

// -- Step Progress Indicator --

@Preview(showBackground = true)
@Composable
fun StepProgressIndicatorPreview() {
    MaterialTheme {
        StepProgressIndicator(
            currentStep = 3,
            totalSteps = 9,
            stepLabel = "Passo 3 de 9"
        )
    }
}

// -- Wizard Steps --

@Preview(showBackground = true)
@Composable
fun CreatePermitStep1Preview() {
    MaterialTheme {
        CreatePermitStep1Screen(
            viewModel = WorkPermitViewModel(),
            strings = Translations.portuguese,
            onNext = {},
            onCancel = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CreatePermitStep2Preview() {
    MaterialTheme {
        CreatePermitStep2Screen(
            viewModel = WorkPermitViewModel(),
            strings = Translations.portuguese,
            onNext = {},
            onPrevious = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CreatePermitStep3Preview() {
    MaterialTheme {
        CreatePermitStep3Screen(
            viewModel = WorkPermitViewModel(),
            strings = Translations.portuguese,
            onNext = {},
            onPrevious = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CreatePermitStep4Preview() {
    MaterialTheme {
        CreatePermitStep4Screen(
            viewModel = WorkPermitViewModel(),
            strings = Translations.portuguese,
            onNext = {},
            onPrevious = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CreatePermitStep5Preview() {
    MaterialTheme {
        CreatePermitStep5Screen(
            viewModel = WorkPermitViewModel(),
            strings = Translations.portuguese,
            onNext = {},
            onPrevious = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CreatePermitStep6Preview() {
    MaterialTheme {
        CreatePermitStep6Screen(
            viewModel = WorkPermitViewModel(),
            strings = Translations.portuguese,
            onNext = {},
            onPrevious = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CreatePermitStep7Preview() {
    MaterialTheme {
        CreatePermitStep7Screen(
            viewModel = WorkPermitViewModel(),
            strings = Translations.portuguese,
            onNext = {},
            onPrevious = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CreatePermitStep8Preview() {
    MaterialTheme {
        CreatePermitStep8Screen(
            viewModel = WorkPermitViewModel(),
            strings = Translations.portuguese,
            onNext = {},
            onPrevious = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CreatePermitStep9Preview() {
    MaterialTheme {
        CreatePermitStep9Screen(
            viewModel = WorkPermitViewModel(),
            strings = Translations.portuguese,
            createdBy = "admin",
            onSendForApproval = {},
            onPrevious = {}
        )
    }
}

// -- Post-submit Screens --

@Preview(showBackground = true)
@Composable
fun SubmittingPermitScreenPreview() {
    MaterialTheme {
        SubmittingPermitScreen(
            strings = Translations.portuguese
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PermitResultScreenPreview() {
    MaterialTheme {
        PermitResultScreen(
            viewModel = WorkPermitViewModel(),
            strings = Translations.portuguese,
            onBackToHome = {}
        )
    }
}

// -- Stub Screens --

@Preview(showBackground = true)
@Composable
fun MyPermitsScreenPreview() {
    MaterialTheme {
        MyPermitsScreen(
            strings = Translations.portuguese,
            onBack = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MyApprovalsScreenPreview() {
    MaterialTheme {
        MyApprovalsScreen(
            strings = Translations.portuguese,
            onBack = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MyAccountScreenPreview() {
    MaterialTheme {
        MyAccountScreen(
            currentUser = com.cng.carfelchemsafety.model.User(
                id = "abc123",
                username = "Carlos",
                email = "carlos@email.com",
                passwordHash = ""
            ),
            profileUpdateState = com.cng.carfelchemsafety.auth.ProfileUpdateResult.Idle,
            passwordChangeState = com.cng.carfelchemsafety.auth.PasswordChangeResult.Idle,
            strings = Translations.portuguese,
            onSaveClick = { _, _ -> },
            onChangePasswordClick = { _, _, _ -> },
            onLogoutClick = {},
            onBack = {}
        )
    }
}
