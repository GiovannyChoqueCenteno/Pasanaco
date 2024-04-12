package com.giovanny.pasanaco.feature_pasanaco.domain.use_case.participante

import com.giovanny.pasanaco.feature_pasanaco.domain.model.Participante
import com.giovanny.pasanaco.feature_pasanaco.domain.repository.ParticipanteRepository

class GetParticipante(
    private val repository: ParticipanteRepository
) {
    suspend operator fun invoke(participanteId: Long): Participante? {
        return repository.getParticipante(participanteId)
    }
}