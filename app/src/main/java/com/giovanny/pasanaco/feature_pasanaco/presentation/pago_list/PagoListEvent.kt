package com.giovanny.pasanaco.feature_pasanaco.presentation.pago_list

sealed class PagoListEvent {
    object ToggleDialog : PagoListEvent()
    data class DeletePago(val pagoId: Long) : PagoListEvent()
}