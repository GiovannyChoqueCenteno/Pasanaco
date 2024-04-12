package com.giovanny.pasanaco.feature_pasanaco.data.repository

import com.giovanny.pasanaco.feature_pasanaco.data.datasource.PagoDao
import com.giovanny.pasanaco.feature_pasanaco.domain.model.Pago
import com.giovanny.pasanaco.feature_pasanaco.domain.model.PagoParticipante
import com.giovanny.pasanaco.feature_pasanaco.domain.repository.PagoRepository
import kotlinx.coroutines.flow.Flow

class PagoRepositoryImpl(
    private val dao: PagoDao
) : PagoRepository {
    override fun getPagos(): Flow<List<PagoParticipante>> {
        return dao.getPagos()
    }

    override suspend fun addPago(pago: Pago) {
        dao.addPago(pago)
    }

    override fun getPagosDiaActivo(): Flow<List<PagoParticipante>> {
        return dao.getPagosDiaActivo()
    }

    override suspend fun deletePago(pagoId: Long): Int {
        return dao.deletePago(pagoId)
    }

    override suspend fun getPago(pagoId: Long): PagoParticipante? {
        return dao.getPago(pagoId)
    }
}