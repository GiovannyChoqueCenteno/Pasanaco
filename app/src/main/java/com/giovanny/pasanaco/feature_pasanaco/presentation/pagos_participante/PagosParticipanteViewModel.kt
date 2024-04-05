package com.giovanny.pasanaco.feature_pasanaco.presentation.pagos_participante

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.giovanny.pasanaco.feature_pasanaco.domain.use_case.participante.ParticipanteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class PagosParticipanteViewModel @Inject constructor(
    private val participanteUseCases: ParticipanteUseCases
) : ViewModel() {
    private val _state = MutableStateFlow(PagosParticipanteState())
    val state = _state.asStateFlow()
    private var getParticipantesJob: Job? = null

    init {
        getParticipantes()
    }

    fun getParticipantes() {
        getParticipantesJob?.cancel()
        getParticipantesJob =
            participanteUseCases.getPagosDiaActivoByParticipante().onEach { pagos ->
                _state.update {
                    it.copy(pagosParticipante = pagos, isLoading = false)
                }
            }.launchIn(viewModelScope)
    }
}
