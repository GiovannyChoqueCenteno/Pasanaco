package com.giovanny.pasanaco.feature_pasanaco.domain.repository

import com.giovanny.pasanaco.feature_pasanaco.domain.model.Pago
import kotlinx.coroutines.flow.Flow

interface PagoRepository {
    fun getPagos(): Flow<List<Pago>>
}