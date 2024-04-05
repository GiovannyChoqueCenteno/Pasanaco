package com.giovanny.pasanaco.feature_pasanaco.domain.use_case.participante

import com.giovanny.pasanaco.feature_pasanaco.domain.model.PagoParticipanteDia
import com.giovanny.pasanaco.feature_pasanaco.domain.repository.ParticipanteRepository
import kotlinx.coroutines.flow.Flow

class GetPagosDiaActivoByParticipante(
    private val repository: ParticipanteRepository
) {
    operator fun invoke(): Flow<List<PagoParticipanteDia>> {
        return repository.getPagosDiaActivoByParticipante()
    }
}