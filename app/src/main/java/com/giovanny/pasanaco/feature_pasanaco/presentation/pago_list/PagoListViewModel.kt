package com.giovanny.pasanaco.feature_pasanaco.presentation.pago_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.giovanny.pasanaco.feature_pasanaco.domain.model.Pago
import com.giovanny.pasanaco.feature_pasanaco.domain.use_case.dia.DiaUseCases
import com.giovanny.pasanaco.feature_pasanaco.domain.use_case.pago.PagoUseCases
import com.giovanny.pasanaco.feature_pasanaco.presentation.new_pago.NewPagoViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PagoListViewModel @Inject constructor(
    private val pagoUseCases: PagoUseCases,
    private val diaUseCases: DiaUseCases
) : ViewModel() {

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private val _state = MutableStateFlow(PagoListState())
    val state = _state.asStateFlow()
    private var getPagosJob: Job? = null

    init {
        getPagos()
    }

    private fun getPagos() {
        _state.update { _state.value.copy(isLoading = true) }
        getPagosJob?.cancel()
        getPagosJob = pagoUseCases.getPagos().onEach { result ->
            _state.update { it.copy(pagoList = result, isLoading = false) }
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: PagoListEvent) {
        when (event) {
            is PagoListEvent.DeletePago -> {
                viewModelScope.launch {
                    pagoUseCases.deletePago(event.pagoId)
                    _state.update {
                        it.copy(showDialog = false)
                    }
                }
            }

            is PagoListEvent.ToggleDialog -> {
                _state.update {
                    it.copy(showDialog = !it.showDialog, textDialog = "Desea eliminar este pago")
                }
            }
        }
    }

    fun diaActivo() {
        viewModelScope.launch {
            try {
                val dia = diaUseCases.diaActivo()
                if (dia != null) {
                    _eventFlow.emit(UiEvent.NewPago)
                } else {
                    _eventFlow.emit(
                        UiEvent.ShowSnackbar(
                            message = "Dia no activado"
                        )
                    )
                }
            } catch (e: Exception) {
                _eventFlow.emit(
                    UiEvent.ShowSnackbar(
                        message = e.message ?: "Couldn't save note"
                    )
                )
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String) : UiEvent()
        data object NewPago : UiEvent()
    }
}