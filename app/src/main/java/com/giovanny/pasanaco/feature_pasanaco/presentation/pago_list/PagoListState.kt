package com.giovanny.pasanaco.feature_pasanaco.presentation.pago_list

import com.giovanny.pasanaco.feature_pasanaco.domain.model.Pago
import com.giovanny.pasanaco.feature_pasanaco.domain.model.PagoParticipante

data class PagoListState(
    val pagoList: List<PagoParticipante> = listOf(),
    val isLoading: Boolean = false,
    val showDialog: Boolean = false,
    val textDialog: String = ""
)