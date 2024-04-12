package com.giovanny.pasanaco.feature_pasanaco.domain.use_case.pago

import com.giovanny.pasanaco.feature_pasanaco.domain.model.Pago
import com.giovanny.pasanaco.feature_pasanaco.domain.model.PagoParticipante
import com.giovanny.pasanaco.feature_pasanaco.domain.repository.PagoRepository

class GetPago(
    private val repository: PagoRepository
) {
    suspend operator fun invoke(pagoId: Long): PagoParticipante? {
        return repository.getPago(pagoId)
    }
}