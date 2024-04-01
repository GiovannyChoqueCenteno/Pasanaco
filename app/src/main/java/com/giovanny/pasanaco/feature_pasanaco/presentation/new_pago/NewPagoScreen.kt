package com.giovanny.pasanaco.feature_pasanaco.presentation.new_pago

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material.icons.outlined.Save
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewPagoScreen(
    navController: NavController,
    onComposing: (AppBarState) -> Unit,
    snackbarHostState : SnackbarHostState,
    viewModel: NewPagoViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
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
                        viewModel.onEvent(NewPagoEvent.ToggleShowDialogCliente())
                    },
                enabled = false,
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.ArrowDropDown,
                        contentDescription = "Seleccionar"
                    )
                },
                value = state.participanteDes, onValueChange = {
                    viewModel.onEvent(NewPagoEvent.EnterDescripcion(it))
                })
            TextField(
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier.fillMaxWidth(),
                value = state.monto.toString(), onValueChange = {
                    viewModel.onEvent(NewPagoEvent.EnterMonto(it))
                })
            TextField(
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                ),
                modifier = Modifier.fillMaxWidth(),
                value = state.descripcion, onValueChange = {
                    viewModel.onEvent(NewPagoEvent.EnterDescripcion(it))
                })
            Box(modifier = Modifier.fillMaxWidth()) {
                FilledIconButton(
                    modifier = Modifier.align(Alignment.BottomEnd),
                    onClick = {
                        viewModel.onEvent(NewPagoEvent.SavePago())
                    }) {
                    Icon(imageVector = Icons.Outlined.Save, contentDescription = "Save")
                }
            }
        }

        if (state.showClienteDialog) {
            Dialog(onDismissRequest = {
                viewModel.onEvent(NewPagoEvent.ToggleShowDialogCliente())
            }) {
                ParticipantesDialog(onDismissRequest = {
                    viewModel.onEvent(NewPagoEvent.ToggleShowDialogCliente())
                }, onSelectedParticipante = { participanteId, participanteDes ->
                    viewModel.onEvent(
                        NewPagoEvent.SelectParticipante(
                            participanteId,
                            participanteDes
                        )
                    )
                })
            }
        }
    }
}

