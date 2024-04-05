package com.giovanny.pasanaco.feature_pasanaco.domain.use_case.dia

import com.giovanny.pasanaco.feature_pasanaco.domain.model.Dia
import com.giovanny.pasanaco.feature_pasanaco.domain.model.Pago
import com.giovanny.pasanaco.feature_pasanaco.domain.repository.DiaRepository
import com.giovanny.pasanaco.feature_pasanaco.domain.repository.PagoRepository

class AddDia(
    private val repository: DiaRepository
) {
    suspend operator fun invoke(dia: Dia) : Long {
        return repository.addDia(dia)
    }
}