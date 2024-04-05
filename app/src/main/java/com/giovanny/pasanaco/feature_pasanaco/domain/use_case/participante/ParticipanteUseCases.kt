package com.giovanny.pasanaco.feature_pasanaco.domain.use_case.participante

data class ParticipanteUseCases(
    val getPariticipantes: GetPariticipantes,
    val addParticipante: AddParticipante,
    val deleteParticipante: DeleteParticipante,
    val getPagosDiaActivoByParticipante: GetPagosDiaActivoByParticipante
)