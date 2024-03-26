package com.giovanny.pasanaco.feature_pasanaco.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Pasanaco(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val descripcion: String,
    val fechaInicio: String,
    val fechaFin: String,
    val activo: Boolean
)