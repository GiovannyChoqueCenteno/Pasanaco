package com.giovanny.pasanaco.feature_pasanaco.presentation.participantes_dialog

import com.giovanny.pasanaco.feature_pasanaco.domain.model.Participante

data class ParticipantesDialogState(
    val participanteList: List<Participante> = emptyList(),
    val isLoading: Boolean = false
)