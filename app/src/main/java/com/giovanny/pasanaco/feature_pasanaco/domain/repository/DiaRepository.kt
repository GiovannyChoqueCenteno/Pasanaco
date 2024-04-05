package com.giovanny.pasanaco.feature_pasanaco.domain.repository

import com.giovanny.pasanaco.feature_pasanaco.domain.model.Dia
import com.giovanny.pasanaco.feature_pasanaco.domain.model.Pago
import kotlinx.coroutines.flow.Flow

interface DiaRepository {
    fun getDias(): Flow<List<Dia>>
    suspend fun diaActivo(): Dia?
    suspend fun addDia(pago: Dia): Long
    suspend fun closeDia(diaId: Long)
    suspend fun getCountDia(): Int
}