package com.giovanny.pasanaco.feature_pasanaco.presentation.options

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.giovanny.pasanaco.feature_pasanaco.domain.model.Dia
import com.giovanny.pasanaco.feature_pasanaco.domain.use_case.dia.DiaUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OptionViewModel @Inject constructor(
    private val diaUseCases: DiaUseCases
) : ViewModel() {

    private val _state = MutableStateFlow(OptionState())
    var state = _state.asStateFlow()

    init {
        diasActivo()
    }

    fun onEvent(event: OptionEvent) {
        when (event) {
            OptionEvent.OpenDia -> {
                viewModelScope.launch {
                    val diasActivos = diaUseCases.diaActivo()
                    if (diasActivos != null) {
                        diaUseCases.closeDia(diasActivos.diaId!!)
                        _state.update {
                            it.copy(diaActivo = null, showDialog = false)
                        }
                    } else {
                        val diaCount = diaUseCases.getCountDia()
                        diaUseCases.addDia(
                            Dia(
                                diaNro = diaCount + 1,
                                isActivo = true
                            )
                        )
                        val dia = diaUseCases.diaActivo()
                        _state.update {
                            it.copy(diaActivo = dia, showDialog = false)
                        }
                    }
                }
            }

            OptionEvent.ToggleDialog -> {
                viewModelScope.launch {
                    _state.update {
                        val diaActivo = diaUseCases.diaActivo()
                        if (diaActivo != null) {
                            it.copy(showDialog = !it.showDialog, textDialog = "Cerar Dia")
                        } else {
                            it.copy(showDialog = !it.showDialog, textDialog = "Abrir Dia")
                        }
                    }
                }
            }

            OptionEvent.ClearPasanaco -> {
            }

            OptionEvent.NewPasanaco -> {
                _state.update {
                    it.copy(
                        showDialog = !it.showDialog,
                        textDialog = "¿Desea crear un nuevo pasanaco?"
                    )
                }
            }
        }
    }

    fun diasActivo() {
        viewModelScope.launch {
            val diaActivo = diaUseCases.diaActivo()
            if (diaActivo != null) {
                _state.update {
                    it.copy(diaActivo = diaActivo)
                }
            } else {
                _state.update {
                    it.copy(diaActivo = null)
                }
            }
        }
    }
}