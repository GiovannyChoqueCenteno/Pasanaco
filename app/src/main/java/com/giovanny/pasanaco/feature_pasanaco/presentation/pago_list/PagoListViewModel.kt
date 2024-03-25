package com.giovanny.pasanaco.feature_pasanaco.presentation.pago_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.giovanny.pasanaco.feature_pasanaco.domain.use_case.PagoUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PagoListViewModel @Inject constructor(
    private val pagoUseCases: PagoUseCases
) : ViewModel() {

    private val _state = mutableStateOf(PagoListState())
    val state: State<PagoListState> = _state


    private var getPagosJob: Job? = null

    init {
        getPagos()
    }

    private fun getPagos() {
        getPagosJob?.cancel()
        getPagosJob = pagoUseCases.getPagos()
            .onEach { pagos ->
                _state.value = state.value.copy(
                    pagoList = pagos,
                )
            }
            .launchIn(viewModelScope)
    }
}