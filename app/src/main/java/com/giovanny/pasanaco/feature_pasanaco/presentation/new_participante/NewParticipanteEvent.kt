package com.giovanny.pasanaco.feature_pasanaco.presentation.new_participante


sealed class NewParticipanteEvent {
    class EnterDescripcion(val value: String) : NewParticipanteEvent()
    class EnterMonto(val value: String) : NewParticipanteEvent()
    class SaveParticipante() : NewParticipanteEvent()
}
