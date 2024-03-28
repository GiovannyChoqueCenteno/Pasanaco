package com.giovanny.pasanaco.feature_pasanaco.domain.use_case.participante

import com.giovanny.pasanaco.feature_pasanaco.domain.model.Pago
import com.giovanny.pasanaco.feature_pasanaco.domain.model.Participante
import com.giovanny.pasanaco.feature_pasanaco.domain.repository.PagoRepository
import com.giovanny.pasanaco.feature_pasanaco.domain.repository.ParticipanteRepository
import kotlinx.coroutines.flow.Flow

class GetPariticipantes(
    private val repository: ParticipanteRepository
) {
    operator fun invoke(): Flow<List<Participante>> {
        return repository.getParticipantes()
    }
}