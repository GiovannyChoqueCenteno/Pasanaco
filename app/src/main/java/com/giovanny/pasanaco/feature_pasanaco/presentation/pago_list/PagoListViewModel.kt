package com.giovanny.pasanaco.feature_pasanaco.presentation.pago_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.giovanny.pasanaco.feature_pasanaco.domain.use_case.pago.PagoUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class PagoListViewModel @Inject constructor(
    private val pagoUseCases: PagoUseCases
) : ViewModel() {

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
}