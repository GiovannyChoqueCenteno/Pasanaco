package com.giovanny.pasanaco.feature_pasanaco.presentation.new_participante

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.giovanny.pasanaco.feature_pasanaco.domain.model.InvalidParticipanteException
import com.giovanny.pasanaco.feature_pasanaco.domain.model.Participante
import com.giovanny.pasanaco.feature_pasanaco.domain.use_case.participante.ParticipanteUseCases
import com.giovanny.pasanaco.feature_pasanaco.presentation.new_pago.TIPOPAGO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewParticipanteViewModel @Inject constructor(
    private val participanteUseCases: ParticipanteUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()
    private val _state = MutableStateFlow(NewParticipanteState())
    val state = _state.asStateFlow()

    sealed class UiEvent {
        data class ShowSnackbar(val message: String) : UiEvent()
        data object SaveParticipante : UiEvent()
    }

    init {
        savedStateHandle.get<Long>("participanteId")?.let { participanteId ->
            if (participanteId != -1L) {
                viewModelScope.launch {
                    participanteUseCases.getParticipante(participanteId)?.also { participante ->
                        _state.update {
                            it.copy(
                                participanteId = participante.participanteId ?: 0,
                                descripcion = participante.participanteDes,
                                monto = participante.monto.toString()
                            )
                        }
                    }
                }
            }
        }
    }

    fun onEvent(event: NewParticipanteEvent) {
        when (event) {
            is NewParticipanteEvent.EnterDescripcion -> {
                _state.update {
                    it.copy(descripcion = event.value)
                }
            }

            is NewParticipanteEvent.SaveParticipante -> {
                viewModelScope.launch {
                    try {
                        val participante = Participante(
                            participanteId = state.value.participanteId,
                            participanteDes = state.value.descripcion,
                            estado = true,
                            selected = false,
                            monto = state.value.monto.toDouble()
                        )
                        participanteUseCases.addParticipante(participante)
                        _eventFlow.emit(UiEvent.SaveParticipante)
                    } catch (e: InvalidParticipanteException) {
                        _eventFlow.emit(UiEvent.ShowSnackbar(e.message ?: ""))
                    }
                }
            }

            is NewParticipanteEvent.EnterMonto -> {
                _state.update {
                    it.copy(monto = event.value)
                }
            }
        }
    }
}