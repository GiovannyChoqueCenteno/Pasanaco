package com.giovanny.pasanaco.feature_pasanaco.domain.use_case.dia

import com.giovanny.pasanaco.feature_pasanaco.domain.repository.DiaRepository

class GetCountDia(
    private val repository: DiaRepository
) {
    suspend operator fun invoke(): Int {
        return repository.getCountDia()
    }
}