package com.giovanny.pasanaco.feature_pasanaco.presentation.new_pago

sealed class NewPagoEvent {
    class EnterDescripcion(val value: String) : NewPagoEvent()
    class EnterMonto(val value: String) : NewPagoEvent()
    class ToggleShowDialogCliente() : NewPagoEvent()
    class SelectParticipante(val id: Long, val des: String) : NewPagoEvent()
    class SavePago() : NewPagoEvent()
}
