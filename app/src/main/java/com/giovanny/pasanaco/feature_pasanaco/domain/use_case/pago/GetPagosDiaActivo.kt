package com.giovanny.pasanaco.feature_pasanaco.domain.use_case.pago

import com.giovanny.pasanaco.feature_pasanaco.domain.model.Dia
import com.giovanny.pasanaco.feature_pasanaco.domain.model.Pago
import com.giovanny.pasanaco.feature_pasanaco.domain.model.PagoParticipante
import com.giovanny.pasanaco.feature_pasanaco.domain.repository.DiaRepository
import com.giovanny.pasanaco.feature_pasanaco.domain.repository.PagoRepository
import kotlinx.coroutines.flow.Flow

class GetPagosDiaActivo(
    private val repository: PagoRepository
) {
    operator fun invoke(): Flow<List<PagoParticipante>> {
        return repository.getPagosDiaActivo()
    }
}