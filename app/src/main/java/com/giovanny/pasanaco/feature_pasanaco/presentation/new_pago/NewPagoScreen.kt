package com.giovanny.pasanaco.feature_pasanaco.presentation.new_pago

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material.icons.outlined.Save
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.giovanny.pasanaco.core.AppBarState
import com.giovanny.pasanaco.feature_pasanaco.presentation.participantes_dialog.ParticipantesDialog
import kotlinx.coroutines.flow.collectLatest

@Composable
fun NewPagoScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    onComposing: (AppBarState) -> Unit,
    snackbarHostState: SnackbarHostState,
    viewModel: NewPagoViewModel = hiltViewModel(),
    isDeleting: Boolean = false
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        val lifecycle = lifecycleOwner.lifecycle
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_START) {
                onComposing(
                    AppBarState(
                        title = "Nuevo Pago",
                        isTabItem = false
                    )
                )
            }
        }
        lifecycle.addObserver(observer)
        onDispose {
            lifecycle.removeObserver(observer)
        }
    }
    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is NewPagoViewModel.UiEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }

                is NewPagoViewModel.UiEvent.SaveNote -> {
                    navController.navigateUp()
                }
            }
        }
    }

    val state by viewModel.state.collectAsStateWithLifecycle()
    NewPagoContent(
        modifier = modifier,
        state = state,
        onEvent = {
            viewModel.onEvent(it)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewPagoContent(
    modifier: Modifier,
    state: NewPagoState,
    onEvent: (NewPagoEvent) -> Unit,
) {
    Box(modifier = modifier.padding(horizontal = 20.dp)) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(text = "Nuevo Pago", style = MaterialTheme.typography.titleMedium)
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onEvent(NewPagoEvent.ToggleShowDialogCliente())
                    },
                enabled = false,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = state.showClienteDialog
                    )
                },
                value = state.participanteDes,
                onValueChange = {

                })
            TextField(
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal,
                    imeAction = ImeAction.Next
                ),
                label = {
                    Text(text = "Monto")
                },
                modifier = Modifier.fillMaxWidth(),
                value = state.monto, onValueChange = {
                    onEvent(NewPagoEvent.EnterMonto(it))
                })
            ExposedDropdownMenuBox(
                expanded = state.isExtended,
                onExpandedChange = {
                    onEvent(NewPagoEvent.ToggleDropdown())
                }
            ) {
                TextField(
                    enabled = false,
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor(),
                    value = state.tipoPago.name, onValueChange = {
                    }, trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = state.isExtended)
                    })
                ExposedDropdownMenu(expanded = state.isExtended, onDismissRequest = {
                    onEvent(NewPagoEvent.ToggleDropdown())
                }) {
                    TIPOPAGO.values().forEach { tipoPago ->
                        DropdownMenuItem(text = {
                            Text(text = tipoPago.name)
                        }, onClick = {
                            onEvent(NewPagoEvent.EnterTipoPago(tipoPago))
                        })
                    }
                }
            }
            Box(modifier = Modifier.fillMaxWidth()) {
                FilledTonalButton(
                    modifier = Modifier
                        .fillMaxWidth(),
                    onClick = {
                        onEvent(NewPagoEvent.SavePago())
                    }) {
                    Text(text = "Guardar")
                }
            }
        }

        if (state.showClienteDialog) {
            ParticipantesDialog(
                onDismissRequest = {
                    onEvent(NewPagoEvent.ToggleShowDialogCliente())
                }, onSelectedParticipante = { participanteId, participanteDes ->
                    onEvent(
                        NewPagoEvent.SelectParticipante(
                            participanteId,
                            participanteDes
                        )
                    )
                })
        }
    }
}

@Preview
@Composable
fun NewPagoPreview() {
    NewPagoContent(modifier = Modifier.fillMaxSize(), state = NewPagoState(
        isExtended = true
    ), onEvent = {

    })
}
