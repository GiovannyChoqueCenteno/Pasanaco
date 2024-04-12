package com.giovanny.pasanaco.feature_pasanaco.domain.repository

import com.giovanny.pasanaco.feature_pasanaco.domain.model.Pago
import com.giovanny.pasanaco.feature_pasanaco.domain.model.PagoParticipanteDia
import com.giovanny.pasanaco.feature_pasanaco.domain.model.Participante
import kotlinx.coroutines.flow.Flow

interface ParticipanteRepository {
    fun getParticipantes(): Flow<List<Participante>>
    suspend fun addParticipante(participante: Participante)
    suspend fun deleteParticipante(participanteId: Long): Int
    fun getPagosDiaActivoByParticipante(): Flow<List<PagoParticipanteDia>>
    suspend fun getParticipante(participanteId: Long): Participante?
}