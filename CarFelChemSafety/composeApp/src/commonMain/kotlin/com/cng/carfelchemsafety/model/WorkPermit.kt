package com.cng.carfelchemsafety.model

enum class EnvironmentalCategory {
    DOCUMENTOS,
    ELETRICA
}

enum class ExposureOption {
    RUIDO,
    POEIRA,
    QUIMICO,
    BIOLOGICO,
    RADIACAO,
    VIBRAÇÃO,
    CALOR,
    FRIO
}

enum class EliminationOption {
    CORTE,
    CHOQUE,
    CURTO_CIRCUITO,
    ARCO_ELETRICO,
    BLOQUEIO,
    SINALIZACAO
}

enum class AssociatedWorkType {
    HEIGHT_WORK,
    HOT_WORK,
    AT_LT_VR
}

data class WorkPermit(
    val id: String,
    val workDescription: String,
    val environmentalCategory: EnvironmentalCategory?,
    val subcategories: List<String>,
    val documentDetails: String,
    val supervisionExecution: String,
    val itemsToCheck: List<String>,
    val exposureOptions: List<ExposureOption>,
    val eliminationOptions: List<EliminationOption>,
    val photoPlaceholder: Boolean,
    val requirementsChecklist: List<String>,
    val observations: String,
    val responsibleName: String,
    val responsibleRole: String,
    val healthInfo: String,
    val associatedWorkType: AssociatedWorkType?,
    val heightActivities: String,
    val anchorageInfo: String,
    val areaResponsible: String,
    val createdBy: String,
    val createdAt: Long = 0L
)
