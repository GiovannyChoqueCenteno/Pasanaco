package com.giovanny.pasanaco.feature_pasanaco.domain.use_case.participante

import com.giovanny.pasanaco.feature_pasanaco.domain.model.InvalidParticipanteException
import com.giovanny.pasanaco.feature_pasanaco.domain.model.Participante
import com.giovanny.pasanaco.feature_pasanaco.domain.repository.ParticipanteRepository

class AddParticipante constructor(
    private val repository: ParticipanteRepository
) {
    @Throws(InvalidParticipanteException::class)
    suspend operator fun invoke(participante: Participante) {
        if (participante.participanteDes.isBlank()) {
            throw InvalidParticipanteException("La descripcion no puede estar vacia")
        }
        repository.addParticipante(participante)
    }
}