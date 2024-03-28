package com.giovanny.pasanaco.feature_pasanaco.domain.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
data class Participante(
    @PrimaryKey(autoGenerate = true)
    val partipanteId: Long?,
    val participanteDes: String,
    val estado: Boolean,
    @Ignore
    val selected: Boolean
) {
    constructor(
        partipanteId: Long,
        participanteDes: String,
        estado: Boolean,
    ) : this(
        partipanteId,
        participanteDes,
        estado, false
    )

}