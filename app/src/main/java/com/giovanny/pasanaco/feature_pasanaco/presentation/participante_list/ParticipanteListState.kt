package com.giovanny.pasanaco.feature_pasanaco.presentation.participante_list

import com.giovanny.pasanaco.feature_pasanaco.domain.model.Participante

data class ParticipanteListState(
    val isLoading: Boolean = false,
    val participanteList: List<Participante>
)