package com.giovanny.pasanaco.feature_pasanaco.domain.model

import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class PagoParticipante(
    val monto: Double,
    val tipoPago: Int,
    val participanteId: Long,
    val pagoId: Long? = null,
    val diaId: Long,
    val participanteDes: String,
    val date: LocalDateTime
) {
    val createdDatTimeFormatted: String
        get() = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy H:m "))
}