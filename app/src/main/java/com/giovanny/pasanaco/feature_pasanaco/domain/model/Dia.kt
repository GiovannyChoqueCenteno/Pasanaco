package com.giovanny.pasanaco.feature_pasanaco.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Dia(
    @PrimaryKey
    val diaId: Long? = null,
    val diaNro: Int,
    val isActivo: Boolean,
)