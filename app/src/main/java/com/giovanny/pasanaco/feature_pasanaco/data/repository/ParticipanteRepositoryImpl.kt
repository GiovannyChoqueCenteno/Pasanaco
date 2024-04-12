package com.giovanny.pasanaco.feature_pasanaco.data.repository

import com.giovanny.pasanaco.feature_pasanaco.data.datasource.ParticipanteDao
import com.giovanny.pasanaco.feature_pasanaco.domain.model.PagoParticipanteDia
import com.giovanny.pasanaco.feature_pasanaco.domain.model.Participante
import com.giovanny.pasanaco.feature_pasanaco.domain.repository.ParticipanteRepository
import kotlinx.coroutines.flow.Flow

class ParticipanteRepositoryImpl(
    private val dao: ParticipanteDao
) : ParticipanteRepository {

    override fun getParticipantes(): Flow<List<Participante>> {
        return dao.getParticipantes()
    }

    override suspend fun addParticipante(participante: Participante) {
        return dao.addParticipante(participante)
    }

    override suspend fun deleteParticipante(participanteId: Long): Int {
        return dao.deleteParticipante(participanteId)
    }

    override fun getPagosDiaActivoByParticipante(): Flow<List<PagoParticipanteDia>> {
        return dao.getPagosDiaActivoByParticipante()
    }

    override suspend fun getParticipante(participanteId: Long): Participante? {
        return dao.getParticipante(participanteId)
    }
}