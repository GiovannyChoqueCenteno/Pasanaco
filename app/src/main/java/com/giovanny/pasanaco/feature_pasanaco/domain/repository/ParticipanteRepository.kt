package com.giovanny.pasanaco.feature_pasanaco.domain.repository

import com.giovanny.pasanaco.feature_pasanaco.domain.model.Pago
import com.giovanny.pasanaco.feature_pasanaco.domain.model.Participante
import kotlinx.coroutines.flow.Flow

interface ParticipanteRepository {
    fun getParticipantes(): Flow<List<Participante>>
}