package com.giovanny.pasanaco.feature_pasanaco.domain.use_case.pago

import com.giovanny.pasanaco.feature_pasanaco.domain.repository.PagoRepository

class DeletePago(
    private val repository: PagoRepository
) {
    suspend operator fun invoke(pagoId: Long) : Int{
        return repository.deletePago(pagoId)
    }
}