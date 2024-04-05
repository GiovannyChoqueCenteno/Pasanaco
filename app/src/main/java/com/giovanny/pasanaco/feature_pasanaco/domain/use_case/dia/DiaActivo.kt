package com.giovanny.pasanaco.feature_pasanaco.domain.use_case.dia

import com.giovanny.pasanaco.feature_pasanaco.domain.model.Dia
import com.giovanny.pasanaco.feature_pasanaco.domain.repository.DiaRepository
import kotlinx.coroutines.flow.Flow

class DiaActivo(
    private val repository: DiaRepository
) {
    suspend operator fun invoke() : Dia? {
        return repository.diaActivo()
    }
}