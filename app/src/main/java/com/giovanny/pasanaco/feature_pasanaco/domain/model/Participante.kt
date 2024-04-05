package com.giovanny.pasanaco.feature_pasanaco.domain.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
data class Participante(
    @PrimaryKey(autoGenerate = true)
    val participanteId: Long? = null,
    val participanteDes: String,
    val estado: Boolean,
    val monto: Double,
    @Ignore
    val selected: Boolean
) {
    constructor(
        participanteId: Long,
        participanteDes: String,
        monto: Double,
        estado: Boolean,
    ) : this(
        participanteId,
        participanteDes,
        estado,
        monto,
        false
    )

}

class InvalidParticipanteException(message: String) : Exception(message)