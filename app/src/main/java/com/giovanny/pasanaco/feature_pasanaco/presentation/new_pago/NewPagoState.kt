package com.giovanny.pasanaco.feature_pasanaco.presentation.new_pago

data class NewPagoState(
    val descripcion: String = "",
    val monto: String = "",
    val tipoPago: Int = 0,
    val participanteId: Long = 0,
    val participanteDes: String = "",
    val showClienteDialog: Boolean = false
)