package com.giovanny.pasanaco.feature_pasanaco.domain.repository

import com.giovanny.pasanaco.feature_pasanaco.domain.model.Dia
import com.giovanny.pasanaco.feature_pasanaco.domain.model.Pago
import com.giovanny.pasanaco.feature_pasanaco.domain.model.PagoParticipante
import kotlinx.coroutines.flow.Flow

interface PagoRepository {
    fun getPagos(): Flow<List<PagoParticipante>>
    suspend fun addPago(pago: Pago)
    fun getPagosDiaActivo(): Flow<List<PagoParticipante>>
}