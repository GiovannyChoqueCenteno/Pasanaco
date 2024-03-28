package com.giovanny.pasanaco.feature_pasanaco.data.repository

import com.giovanny.pasanaco.feature_pasanaco.data.datasource.PagoDao
import com.giovanny.pasanaco.feature_pasanaco.domain.model.Pago
import com.giovanny.pasanaco.feature_pasanaco.domain.repository.PagoRepository
import kotlinx.coroutines.flow.Flow

class PagoRepositoryImpl(
    private val dao: PagoDao
) : PagoRepository {
    override fun getPagos(): Flow<List<Pago>> {
        return dao.getPagos()
    }

    override suspend fun addPago(pago: Pago) {
        dao.addPago(pago)
    }
}