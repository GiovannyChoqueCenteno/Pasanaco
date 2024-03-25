package com.giovanny.pasanaco.feature_pasanaco.presentation.pago_list

import com.giovanny.pasanaco.feature_pasanaco.domain.model.Pago

data class PagoListState(
    val pagoList: List<Pago> = listOf(),
    val isLoading: Boolean = false
)