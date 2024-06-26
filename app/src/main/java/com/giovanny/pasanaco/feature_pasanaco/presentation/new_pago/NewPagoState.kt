package com.giovanny.pasanaco.feature_pasanaco.presentation.new_pago

data class NewPagoState(
    val pagoId: Long = 0,
    val monto: String = "",
    val tipoPago: TIPOPAGO = TIPOPAGO.QR,
    val participanteId: Long = 0,
    val participanteDes: String = "",
    val showClienteDialog: Boolean = false,
    val isExtended: Boolean = false,
)