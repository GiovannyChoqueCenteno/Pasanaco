package com.giovanny.pasanaco.feature_pasanaco.presentation.pago_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.giovanny.pasanaco.Screen
import com.giovanny.pasanaco.core.AppBarState
import com.giovanny.pasanaco.feature_pasanaco.domain.model.Pago
import com.giovanny.pasanaco.feature_pasanaco.domain.model.PagoParticipante
import com.giovanny.pasanaco.feature_pasanaco.presentation.new_pago.NewPagoViewModel
import com.giovanny.pasanaco.feature_pasanaco.presentation.participante_list.ParticipanteListEvent
import kotlinx.coroutines.flow.collectLatest
import java.time.LocalDateTime

@Composable
fun PagoListScreen(
    onComposing: (AppBarState) -> Unit,
    navController: NavController,
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier,
    viewModel: PagoListViewModel = hiltViewModel()
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        val lifecycle = lifecycleOwner.lifecycle
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_START) {
                onComposing(
                    AppBarState(
                        title = "Pagos",
                        floattingActionButton = {
                            FloatingActionButton(
                                modifier = Modifier.clip(CircleShape),
                                onClick = {
                                    viewModel.diaActivo()
                                }) {
                                Icon(imageVector = Icons.Default.AddCircle, contentDescription = "")
                            }
                        },
                        isTabItem = true
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
                is PagoListViewModel.UiEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }

                is PagoListViewModel.UiEvent.NewPago -> {
                    navController.navigate(Screen.NuevoPago.route)
                }
            }
        }
    }
    val state by viewModel.state.collectAsStateWithLifecycle()
    PagoListContent(
        modifier = modifier,
        state = state,
        onNavigate = {
            navController.navigate(it)
        },
        onEvent = {
            viewModel.onEvent(it)
        }
    )
}

@Composable
fun PagoListContent(
    modifier: Modifier = Modifier,
    state: PagoListState,
    onNavigate: (String) -> Unit,
    onEvent: (PagoListEvent) -> Unit
) {
    Box(
        modifier = modifier
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    horizontal = 10.dp
                ),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(state.pagoList) { pago ->
                PagoCard(
                    pago = pago,
                    onNavigate = {
                        onNavigate(it)
                    },
                    onDeleting = {
                        onEvent(PagoListEvent.ToggleDialog)
                    },
                    isEditable = true

                )
                if (state.showDialog)
                    AlertDialog(
                        title = {
                            Text(text = state.textDialog)
                        },
                        text = {
                            Text(text = state.textDialog)
                        },
                        onDismissRequest = {
                            onEvent(PagoListEvent.ToggleDialog)
                        },
                        dismissButton = {
                            TextButton(
                                onClick = {
                                    onEvent.invoke(PagoListEvent.ToggleDialog)
                                }
                            ) {
                                Text("Cancelar")
                            }
                        },
                        confirmButton = {
                            TextButton(
                                onClick = {
                                    onEvent.invoke(
                                        PagoListEvent.DeletePago(
                                            pago.pagoId!!
                                        )
                                    )
                                }
                            ) {
                                Text("Aceptar")
                            }
                        },
                    )
            }
        }
    }
    if (state.isLoading) {
        CircularProgressIndicator()
    }
}

@Preview
@Composable
fun PagoListPreview() {
    PagoListContent(
        state = PagoListState(
            pagoList = listOf(
                PagoParticipante(
                    monto = 12.5,
                    tipoPago = 1,
                    participanteId = 1,
                    pagoId = 1,
                    diaId = 1,
                    date = LocalDateTime.now(),
                    participanteDes = "Pepe"
                )
            )
        ),
        onNavigate = {},
        onEvent = {

        }
    )
}