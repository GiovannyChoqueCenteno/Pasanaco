package com.giovanny.pasanaco.feature_pasanaco.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Pago(
    val descripcion: String,
    val monto: Double,
    val tipoPago: Int,
    @PrimaryKey
    val Id: Int? = null
)