package com.cng.carfelchemsafety.viewmodel

import com.cng.carfelchemsafety.auth.WorkPermitResult
import com.cng.carfelchemsafety.model.AssociatedWorkType
import com.cng.carfelchemsafety.model.EliminationOption
import com.cng.carfelchemsafety.model.EnvironmentalCategory
import com.cng.carfelchemsafety.model.ExposureOption
import com.cng.carfelchemsafety.model.WorkPermit
import com.cng.carfelchemsafety.model.WorkPermitFormState
import com.cng.carfelchemsafety.repository.LocalWorkPermitRepository
import com.cng.carfelchemsafety.repository.WorkPermitRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WorkPermitViewModel(
    private val repository: WorkPermitRepository = LocalWorkPermitRepository()
) {
    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    private val _formState = MutableStateFlow(WorkPermitFormState())
    val formState: StateFlow<WorkPermitFormState> = _formState.asStateFlow()

    private val _submitResult = MutableStateFlow<WorkPermitResult>(WorkPermitResult.Idle)
    val submitResult: StateFlow<WorkPermitResult> = _submitResult.asStateFlow()

    // Step 1
    fun updateWorkDescription(value: String) {
        _formState.value = _formState.value.copy(workDescription = value)
    }

    fun updateEnvironmentalCategory(category: EnvironmentalCategory?) {
        _formState.value = _formState.value.copy(environmentalCategory = category)
    }

    fun updateSubcategoryDocumentos(checked: Boolean) {
        _formState.value = _formState.value.copy(subcategoryDocumentos = checked)
    }

    fun updateSubcategoryEletrica(checked: Boolean) {
        _formState.value = _formState.value.copy(subcategoryEletrica = checked)
    }

    fun updateSubcategoryMecanica(checked: Boolean) {
        _formState.value = _formState.value.copy(subcategoryMecanica = checked)
    }

    fun updateSubcategoryHidraulica(checked: Boolean) {
        _formState.value = _formState.value.copy(subcategoryHidraulica = checked)
    }

    fun updateSubcategoryCivil(checked: Boolean) {
        _formState.value = _formState.value.copy(subcategoryCivil = checked)
    }

    // Step 2
    fun updateDocumentDetails(value: String) {
        _formState.value = _formState.value.copy(documentDetails = value)
    }

    fun updateSupervisionExecution(value: String) {
        _formState.value = _formState.value.copy(supervisionExecution = value)
    }

    fun updateCheckItem1(checked: Boolean) {
        _formState.value = _formState.value.copy(checkItem1 = checked)
    }

    fun updateCheckItem2(checked: Boolean) {
        _formState.value = _formState.value.copy(checkItem2 = checked)
    }

    fun updateCheckItem3(checked: Boolean) {
        _formState.value = _formState.value.copy(checkItem3 = checked)
    }

    fun updateCheckItem4(checked: Boolean) {
        _formState.value = _formState.value.copy(checkItem4 = checked)
    }

    fun updateCheckItem5(checked: Boolean) {
        _formState.value = _formState.value.copy(checkItem5 = checked)
    }

    // Step 3
    fun toggleExposureOption(option: ExposureOption) {
        val current = _formState.value.exposureOptions.toMutableSet()
        if (current.contains(option)) current.remove(option) else current.add(option)
        _formState.value = _formState.value.copy(exposureOptions = current)
    }

    fun toggleEliminationOption(option: EliminationOption) {
        val current = _formState.value.eliminationOptions.toMutableSet()
        if (current.contains(option)) current.remove(option) else current.add(option)
        _formState.value = _formState.value.copy(eliminationOptions = current)
    }

    // Step 4
    fun updatePhotoTaken(taken: Boolean) {
        _formState.value = _formState.value.copy(photoTaken = taken)
    }

    // Step 5
    fun updateRequirement1(checked: Boolean) {
        _formState.value = _formState.value.copy(requirement1 = checked)
    }

    fun updateRequirement2(checked: Boolean) {
        _formState.value = _formState.value.copy(requirement2 = checked)
    }

    fun updateRequirement3(checked: Boolean) {
        _formState.value = _formState.value.copy(requirement3 = checked)
    }

    fun updateRequirement4(checked: Boolean) {
        _formState.value = _formState.value.copy(requirement4 = checked)
    }

    fun updateRequirement5(checked: Boolean) {
        _formState.value = _formState.value.copy(requirement5 = checked)
    }

    fun updateRequirement6(checked: Boolean) {
        _formState.value = _formState.value.copy(requirement6 = checked)
    }

    fun updateObservations(value: String) {
        _formState.value = _formState.value.copy(observations = value)
    }

    // Step 6
    fun updateResponsibleName(value: String) {
        _formState.value = _formState.value.copy(responsibleName = value)
    }

    fun updateResponsibleRole(value: String) {
        _formState.value = _formState.value.copy(responsibleRole = value)
    }

    fun updateHealthInfo(value: String) {
        _formState.value = _formState.value.copy(healthInfo = value)
    }

    // Step 7
    fun updateAssociatedWorkType(type: AssociatedWorkType?) {
        _formState.value = _formState.value.copy(associatedWorkType = type)
    }

    // Step 8
    fun updateHeightActivities(value: String) {
        _formState.value = _formState.value.copy(heightActivities = value)
    }

    fun updateAnchorageInfo(value: String) {
        _formState.value = _formState.value.copy(anchorageInfo = value)
    }

    // Step 9
    fun updateAreaResponsible(value: String) {
        _formState.value = _formState.value.copy(areaResponsible = value)
    }

    fun updateApproverName(value: String) {
        _formState.value = _formState.value.copy(approverName = value)
    }

    fun updateSingleSignature(checked: Boolean) {
        _formState.value = _formState.value.copy(singleSignature = checked)
    }

    fun requiresHeightWork(): Boolean = _formState.value.requiresHeightWork()

    fun submitPermit(createdBy: String) {
        val state = _formState.value

        viewModelScope.launch {
            _submitResult.value = WorkPermitResult.Loading

            val subcategories = buildList {
                if (state.subcategoryDocumentos) add("Documentos")
                if (state.subcategoryEletrica) add("Eletrica")
                if (state.subcategoryMecanica) add("Mecanica")
                if (state.subcategoryHidraulica) add("Hidraulica")
                if (state.subcategoryCivil) add("Civil")
            }

            val itemsToCheck = buildList {
                if (state.checkItem1) add("Item 1")
                if (state.checkItem2) add("Item 2")
                if (state.checkItem3) add("Item 3")
                if (state.checkItem4) add("Item 4")
                if (state.checkItem5) add("Item 5")
            }

            val requirementsChecklist = buildList {
                if (state.requirement1) add("Req 1")
                if (state.requirement2) add("Req 2")
                if (state.requirement3) add("Req 3")
                if (state.requirement4) add("Req 4")
                if (state.requirement5) add("Req 5")
                if (state.requirement6) add("Req 6")
            }

            val permit = WorkPermit(
                id = "",
                workDescription = state.workDescription,
                environmentalCategory = state.environmentalCategory,
                subcategories = subcategories,
                documentDetails = state.documentDetails,
                supervisionExecution = state.supervisionExecution,
                itemsToCheck = itemsToCheck,
                exposureOptions = state.exposureOptions.toList(),
                eliminationOptions = state.eliminationOptions.toList(),
                photoPlaceholder = state.photoTaken,
                requirementsChecklist = requirementsChecklist,
                observations = state.observations,
                responsibleName = state.responsibleName,
                responsibleRole = state.responsibleRole,
                healthInfo = state.healthInfo,
                associatedWorkType = state.associatedWorkType,
                heightActivities = state.heightActivities,
                anchorageInfo = state.anchorageInfo,
                areaResponsible = state.areaResponsible,
                createdBy = createdBy
            )

            repository.createPermit(permit)
                .onSuccess { created ->
                    _submitResult.value = WorkPermitResult.Success(created)
                }
                .onFailure { exception ->
                    _submitResult.value = WorkPermitResult.Error(
                        exception.message ?: "Erro ao criar PT"
                    )
                }
        }
    }

    fun resetForm() {
        _formState.value = WorkPermitFormState()
        _submitResult.value = WorkPermitResult.Idle
    }

    fun resetSubmitResult() {
        _submitResult.value = WorkPermitResult.Idle
    }
}
