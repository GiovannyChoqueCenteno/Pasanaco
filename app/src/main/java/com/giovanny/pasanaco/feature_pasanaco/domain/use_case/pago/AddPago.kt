package com.giovanny.pasanaco.feature_pasanaco.domain.use_case.pago

import com.giovanny.pasanaco.feature_pasanaco.domain.model.Pago
import com.giovanny.pasanaco.feature_pasanaco.domain.repository.PagoRepository
import kotlinx.coroutines.flow.Flow

class AddPago(
    private val repository: PagoRepository
) {
    suspend operator fun invoke(pago: Pago) {
        return repository.addPago(pago)
    }
}