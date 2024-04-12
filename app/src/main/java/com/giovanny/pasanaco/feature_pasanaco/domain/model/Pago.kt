package com.giovanny.pasanaco.feature_pasanaco.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Entity
data class Pago(
    val monto: Double,
    val tipoPago: Int,
    val participanteId: Long,
    @PrimaryKey(autoGenerate = true)
    val pagoId: Long? = null,
    val diaId: Long,
    val date: LocalDateTime = LocalDateTime.now(),
) {
    val createdDatTimeFormatted: String
        get() = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy H:m"))
}