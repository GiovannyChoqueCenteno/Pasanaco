package com.giovanny.pasanaco.feature_pasanaco.domain.use_case.participante

import com.giovanny.pasanaco.feature_pasanaco.domain.repository.ParticipanteRepository

class DeleteParticipante(
    private val repository: ParticipanteRepository
) {

    suspend operator fun invoke(participanteId: Long): Int {
        return repository.deleteParticipante(participanteId)
    }
}