package com.giovanny.pasanaco.feature_pasanaco.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.giovanny.pasanaco.feature_pasanaco.domain.use_case.dia.DiaUseCases
import com.giovanny.pasanaco.feature_pasanaco.domain.use_case.pago.PagoUseCases
import com.giovanny.pasanaco.feature_pasanaco.presentation.pago_list.PagoListState
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
class HomeViewModel @Inject constructor(
    private val pagoUseCases: PagoUseCases,
    private val diaUseCases: DiaUseCases
) : ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()
    private var getPagosJob: Job? = null

    init {
        getPagos()
    }

    private fun getPagos() {
        _state.update { _state.value.copy(isLoading = true) }
        getPagosJob?.cancel()
        viewModelScope.launch {
            val dia = diaUseCases.diaActivo()
            if (dia != null) {
                getPagosJob = pagoUseCases.getPagosDiaActivo().onEach { result ->
                    _state.update { it.copy(dia = dia, pagoList = result, isLoading = false) }
                }.launchIn(viewModelScope)
            } else {
                _state.update { it.copy(isLoading = false) }
            }
        }
    }
}