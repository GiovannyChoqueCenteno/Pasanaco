package com.giovanny.pasanaco.feature_pasanaco.domain.use_case.dia

import com.giovanny.pasanaco.feature_pasanaco.domain.repository.DiaRepository

class CloseDia(
    private val repository: DiaRepository
) {
    suspend operator fun invoke(diaId: Long) {
        repository.closeDia(diaId)
    }
}