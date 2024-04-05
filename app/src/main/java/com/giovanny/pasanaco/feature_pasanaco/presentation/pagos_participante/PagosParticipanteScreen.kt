package com.giovanny.pasanaco.feature_pasanaco.presentation.pagos_participante

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.material3.MaterialTheme

import com.giovanny.pasanaco.core.AppBarState

@Composable
fun PagosParticipanteScreen(
    modifier: Modifier = Modifier,
    onComposing: (AppBarState) -> Unit,
    viewModel: PagosParticipanteViewModel = hiltViewModel()
) {
    val lifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(lifecycleOwner) {
        val lifecycle = lifecycleOwner.lifecycle
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_CREATE) {
                onComposing(
                    AppBarState(
                        title = "Pagos de Participantes",
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
    PagosParticipanteContent(
        modifier = modifier,
        state = state
    )
}

@Composable
fun PagosParticipanteContent(
    modifier: Modifier = Modifier,
    state: PagosParticipanteState
) {
    Box(modifier = modifier.padding(horizontal = 10.dp)) {
        LazyVerticalGrid(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            columns = GridCells.Fixed(2)
        ) {
            items(state.pagosParticipante) { pago ->
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = if ((pago.monto - pago.pago) > 0)
                            MaterialTheme.colorScheme.errorContainer
                        else
                            MaterialTheme.colorScheme.primaryContainer
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp)
                    ) {
                        Text(text = pago.participanteDes)
                        Box(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                modifier = Modifier.align(Alignment.CenterEnd),
                                text = "Pago : " + pago.pago.toString() + " Bs"
                            )
                        }
                        Box(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                modifier = Modifier.align(Alignment.CenterEnd),
                                text = "Debe : " + (pago.monto - pago.pago) + " Bs"
                            )
                        }
                    }
                }
            }
        }
        if (state.isLoading) {
            CircularProgressIndicator()
        }
    }
}

@Preview
@Composable
fun PagosParticipantePreview() {
    PagosParticipanteContent(
        modifier = Modifier.fillMaxSize(),
        state = PagosParticipanteState()
    )
}