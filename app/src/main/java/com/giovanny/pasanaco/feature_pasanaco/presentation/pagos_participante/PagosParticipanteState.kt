package com.giovanny.pasanaco.feature_pasanaco.presentation.pagos_participante

import com.giovanny.pasanaco.feature_pasanaco.domain.model.PagoParticipanteDia

data class PagosParticipanteState(
    val pagosParticipante: List<PagoParticipanteDia> = emptyList(),
    val isLoading: Boolean = false
)