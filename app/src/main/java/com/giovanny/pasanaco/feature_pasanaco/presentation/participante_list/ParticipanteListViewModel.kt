package com.giovanny.pasanaco.feature_pasanaco.presentation.participante_list

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
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ParticipanteListViewModel @Inject constructor(
    private val participanteUseCases: ParticipanteUseCases
) : ViewModel() {

    private val _state = MutableStateFlow(ParticipanteListState())
    val state = _state.asStateFlow()

    private var getParticipantesJob: Job? = null

    init {
        getParticipantes()
    }

    fun onEvent(event: ParticipanteListEvent) {
        when (event) {
            is ParticipanteListEvent.ToggleDialog -> {
                _state.update {
                    it.copy(showDialog = !it.showDialog, textDialog = "Desea eliminar este participante")
                }
            }

            is ParticipanteListEvent.DeleteParticipante -> {
                viewModelScope.launch {
                    participanteUseCases.deleteParticipante(event.participanteId)
                    _state.update {
                        it.copy(showDialog = false)
                    }
                }
            }
        }
    }

    fun getParticipantes() {
        _state.update {
            it.copy(isLoading = true)
        }
        getParticipantesJob = participanteUseCases.getPariticipantes().onEach { participanteList ->
            _state.update {
                it.copy(isLoading = false, participanteList = participanteList)
            }
        }.launchIn(viewModelScope)
    }
}