package com.giovanny.pasanaco.feature_pasanaco.data.repository

import com.giovanny.pasanaco.feature_pasanaco.data.datasource.PagoDao
import com.giovanny.pasanaco.feature_pasanaco.data.datasource.ParticipanteDao
import com.giovanny.pasanaco.feature_pasanaco.domain.model.Pago
import com.giovanny.pasanaco.feature_pasanaco.domain.model.Participante
import com.giovanny.pasanaco.feature_pasanaco.domain.repository.ParticipanteRepository
import kotlinx.coroutines.flow.Flow

class ParticipanteRepositoryImpl(
    private val dao: ParticipanteDao
) : ParticipanteRepository {

    override fun getParticipantes(): Flow<List<Participante>> {
        return dao.getParticipantes()
    }
}