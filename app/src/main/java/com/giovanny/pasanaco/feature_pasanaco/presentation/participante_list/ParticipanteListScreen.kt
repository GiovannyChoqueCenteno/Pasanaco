package com.giovanny.pasanaco.feature_pasanaco.presentation.participante_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
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
import com.giovanny.pasanaco.feature_pasanaco.domain.model.Participante

@Composable
fun ParticipanteListScreen(
    onComposing: (AppBarState) -> Unit,
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: ParticipanteListViewModel = hiltViewModel()
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        val lifecycle = lifecycleOwner.lifecycle
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_START) {
                onComposing(
                    AppBarState(
                        title = "Participantes",
                        floattingActionButton = {
                            FloatingActionButton(
                                modifier = Modifier.clip(CircleShape),
                                onClick = {
                                    navController.navigate(Screen.NuevoParticipante.route)
                                }) {
                                Icon(imageVector = Icons.Default.AddCircle, contentDescription = "")
                            }
                        },
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
    val state by viewModel.state.collectAsStateWithLifecycle()
    ParticipanteListScreenContent(
        modifier = modifier,
        state = state,
        onEvent = {
            viewModel.onEvent(it)
        }
    )
}

@Composable
fun ParticipanteListScreenContent(
    modifier: Modifier = Modifier,
    state: ParticipanteListState,
    onEvent: (ParticipanteListEvent) -> Unit
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    horizontal = 10.dp
                ),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(state.participanteList) { participante ->
                Card {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp)
                    ) {
                        Text(text = participante.participanteDes)
                        Text(text = participante.monto.toString())
                        Icon(
                            modifier = Modifier
                                .align(Alignment.End)
                                .clickable {
                                    onEvent(ParticipanteListEvent.ToggleDialog)
                                },
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Eliminar",
                            tint = MaterialTheme.colorScheme.error
                        )
                    }
                }
                if (state.showDialog)
                    AlertDialog(
                        title = {
                            Text(text = state.textDialog)
                        },
                        text = {
                            Text(text = state.textDialog)
                        },
                        onDismissRequest = {
                            onEvent(ParticipanteListEvent.ToggleDialog)
                        },
                        dismissButton = {
                            TextButton(
                                onClick = {
                                    onEvent.invoke(ParticipanteListEvent.ToggleDialog)
                                }
                            ) {
                                Text("Cancelar")
                            }
                        },
                        confirmButton = {
                            TextButton(
                                onClick = {
                                    onEvent.invoke(
                                        ParticipanteListEvent.DeleteParticipante(
                                            participante.participanteId!!
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

        if (state.isLoading) {
            CircularProgressIndicator()
        }
    }
}

@Preview
@Composable
fun ParticipanteListScreenPreview() {
    ParticipanteListScreenContent(
        modifier = Modifier.fillMaxSize(),
        state = ParticipanteListState(
            isLoading = false,
            participanteList = listOf(
                Participante(
                    participanteId = 1,
                    participanteDes = "descripcipn",
                    monto = 10.0,
                    estado = true,
                ),
                Participante(
                    participanteId = 2,
                    participanteDes = "descripcipn",
                    monto = 10.0,
                    estado = true,
                )
            ),
        ),
        onEvent = {}
    )
}