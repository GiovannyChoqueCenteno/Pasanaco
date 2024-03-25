package com.giovanny.pasanaco.feature_pasanaco.domain.use_case

import com.giovanny.pasanaco.feature_pasanaco.domain.model.Pago
import com.giovanny.pasanaco.feature_pasanaco.domain.repository.PagoRepository
import kotlinx.coroutines.flow.Flow

class GetPagos(
    private val repository: PagoRepository
) {
    operator fun invoke(): Flow<List<Pago>> {
        return repository.getPagos()
    }
}