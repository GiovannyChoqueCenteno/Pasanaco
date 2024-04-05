package com.giovanny.pasanaco.feature_pasanaco.presentation.participante_list

sealed class ParticipanteListEvent {
    object ToggleDialog : ParticipanteListEvent()
    class DeleteParticipante(val participanteId: Long) : ParticipanteListEvent()
}