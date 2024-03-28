package com.giovanny.pasanaco.feature_pasanaco.presentation.new_pago

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.giovanny.pasanaco.feature_pasanaco.domain.model.Pago
import com.giovanny.pasanaco.feature_pasanaco.domain.use_case.pago.PagoUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewPagoViewModel @Inject constructor(
    private val pagoUseCases: PagoUseCases
) : ViewModel() {
    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()
    private val _state = MutableStateFlow(NewPagoState())
    val state = _state.asStateFlow()

    fun onEvent(event: NewPagoEvent) {
        when (event) {
            is NewPagoEvent.EnterDescripcion -> {
                _state.update {
                    it.copy(descripcion = event.value)
                }
            }

            is NewPagoEvent.EnterMonto -> {
                _state.update {
                    it.copy(monto = event.value)
                }
            }

            is NewPagoEvent.ToggleShowDialogCliente -> {
                _state.update {
                    it.copy(showClienteDialog = !it.showClienteDialog)
                }
            }

            is NewPagoEvent.SelectParticipante -> {
                _state.update {
                    it.copy(participanteId = event.id, participanteDes = event.des)
                }
            }

            is NewPagoEvent.SavePago -> {

                viewModelScope.launch {
                    try {
                        val pago = Pago(
                            descripcion = state.value.descripcion,
                            monto = state.value.monto.toDouble(),
                            participanteId = state.value.participanteId,
                            tipoPago = 1
                        )
                        pagoUseCases.addPago(pago)
                        _eventFlow.emit(UiEvent.SaveNote)
                    } catch (e: Exception) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                message = e.message ?: "Couldn't save note"
                            )
                        )
                    }

                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String) : UiEvent()
        object SaveNote : UiEvent()
    }
}

