package com.giovanny.pasanaco.feature_pasanaco.data.repository

import com.giovanny.pasanaco.feature_pasanaco.data.datasource.DiaDao
import com.giovanny.pasanaco.feature_pasanaco.domain.model.Dia
import com.giovanny.pasanaco.feature_pasanaco.domain.repository.DiaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class DiaRepositoryImpl(
    private val dao: DiaDao
) : DiaRepository {
    override fun getDias(): Flow<List<Dia>> {
        return dao.getDias()
    }

    override suspend fun diaActivo(): Dia {
        return withContext(Dispatchers.IO) {
            dao.diaActivo()
        }
    }

    override suspend fun addDia(dia: Dia): Long {
        return dao.addDia(dia)
    }


    override suspend fun closeDia(diaId: Long) {
        dao.closeDia(diaId)
    }

    override suspend fun getCountDia(): Int {
        return withContext(Dispatchers.IO) {
            dao.getCountDia()
        }
    }
}