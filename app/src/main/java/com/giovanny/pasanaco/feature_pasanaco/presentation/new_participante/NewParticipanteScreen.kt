package com.giovanny.pasanaco.feature_pasanaco.presentation.new_participante

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Save
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.giovanny.pasanaco.core.AppBarState
import kotlinx.coroutines.flow.collectLatest

@Composable
fun NewParticipanteScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    onComposing: (AppBarState) -> Unit,
    snackbarHostState: SnackbarHostState,
    viewModel: NewParticipanteViewModel = hiltViewModel(),
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        val lifecycle = lifecycleOwner.lifecycle
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_START) {
                onComposing(
                    AppBarState(
                        title = "Nuevo Participante",
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
                is NewParticipanteViewModel.UiEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }

                is NewParticipanteViewModel.UiEvent.SaveParticipante -> {
                    navController.navigateUp()
                }
            }
        }
    }
    val state by viewModel.state.collectAsStateWithLifecycle()
    NewParticipanteContent(
        modifier = modifier,
        state = state,
        onEvent = {
            viewModel.onEvent(it)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewParticipanteContent(
    modifier: Modifier = Modifier,
    state: NewParticipanteState,
    onEvent: (NewParticipanteEvent) -> Unit
) {
    Box(modifier = modifier.padding(horizontal = 20.dp)) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(text = "Nuevo Participante", style = MaterialTheme.typography.titleMedium)
            TextField(
                keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
                modifier = Modifier
                    .fillMaxWidth(),
                value = state.descripcion,
                onValueChange = {
                    onEvent(NewParticipanteEvent.EnterDescripcion(it))
                }
            )
            TextField(
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                modifier = Modifier
                    .fillMaxWidth(),
                value = state.monto,
                onValueChange = {
                    onEvent(NewParticipanteEvent.EnterMonto(it))
                }
            )
            Box(modifier = Modifier.fillMaxWidth()) {
                FilledTonalButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        onEvent(NewParticipanteEvent.SaveParticipante())
                    }) {
                    Text(text = "Guardar")
                }
            }
        }
    }
}

@Preview
@Composable
fun NewParticipantePreview() {
    NewParticipanteContent(
        modifier = Modifier.fillMaxSize(),
        state = NewParticipanteState(), onEvent = {

        })
}