package com.giovanny.pasanaco.feature_pasanaco.presentation.home

import com.giovanny.pasanaco.feature_pasanaco.domain.model.Dia
import com.giovanny.pasanaco.feature_pasanaco.domain.model.Pago
import com.giovanny.pasanaco.feature_pasanaco.domain.model.PagoParticipante

data class HomeState(
    val pagoList: List<PagoParticipante> = emptyList(),
    val isLoading: Boolean = false,
    val dia: Dia? = null
)