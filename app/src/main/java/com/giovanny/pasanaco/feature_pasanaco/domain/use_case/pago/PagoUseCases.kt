package com.giovanny.pasanaco.feature_pasanaco.domain.use_case.pago

import com.giovanny.pasanaco.feature_pasanaco.domain.use_case.pago.GetPagos

data class PagoUseCases(
    val getPagos: GetPagos,
    val addPago: AddPago
)