package com.cng.carfelchemsafety.model

data class WorkPermitFormState(
    // Step 1 - Basic Info
    val workDescription: String = "",
    val environmentalCategory: EnvironmentalCategory? = null,
    val subcategoryDocumentos: Boolean = false,
    val subcategoryEletrica: Boolean = false,
    val subcategoryMecanica: Boolean = false,
    val subcategoryHidraulica: Boolean = false,
    val subcategoryCivil: Boolean = false,

    // Step 2 - Document Info
    val documentDetails: String = "",
    val supervisionExecution: String = "",
    val checkItem1: Boolean = false,
    val checkItem2: Boolean = false,
    val checkItem3: Boolean = false,
    val checkItem4: Boolean = false,
    val checkItem5: Boolean = false,

    // Step 3 - Exposure/Risks
    val exposureOptions: Set<ExposureOption> = emptySet(),
    val eliminationOptions: Set<EliminationOption> = emptySet(),

    // Step 4 - Photo (placeholder)
    val photoTaken: Boolean = false,

    // Step 5 - General Requirements
    val requirement1: Boolean = false,
    val requirement2: Boolean = false,
    val requirement3: Boolean = false,
    val requirement4: Boolean = false,
    val requirement5: Boolean = false,
    val requirement6: Boolean = false,
    val observations: String = "",

    // Step 6 - Responsible Party
    val responsibleName: String = "",
    val responsibleRole: String = "",
    val healthInfo: String = "",

    // Step 7 - Associated Works
    val associatedWorkType: AssociatedWorkType? = null,

    // Step 8 - Height Work Details
    val heightActivities: String = "",
    val anchorageInfo: String = "",

    // Step 9 - Review & Submit
    val areaResponsible: String = "",
    val approverName: String = "",
    val singleSignature: Boolean = false
) {
    fun isStep1Valid(): Boolean =
        workDescription.isNotBlank() && environmentalCategory != null

    fun isStep2Valid(): Boolean =
        documentDetails.isNotBlank()

    fun isStep6Valid(): Boolean =
        responsibleName.isNotBlank() && responsibleRole.isNotBlank()

    fun isStep8Valid(): Boolean =
        heightActivities.isNotBlank()

    fun isStep9Valid(): Boolean =
        areaResponsible.isNotBlank()

    fun requiresHeightWork(): Boolean =
        associatedWorkType == AssociatedWorkType.HEIGHT_WORK
}
