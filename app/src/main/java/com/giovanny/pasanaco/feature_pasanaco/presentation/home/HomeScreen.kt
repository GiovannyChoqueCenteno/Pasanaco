package com.giovanny.pasanaco.feature_pasanaco.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import com.giovanny.pasanaco.core.AppBarState
import com.giovanny.pasanaco.feature_pasanaco.domain.model.PagoParticipante
import com.giovanny.pasanaco.feature_pasanaco.presentation.pago_list.PagoCard
import java.time.LocalDateTime

@Composable
fun HomeScreen(
    onComposing: (AppBarState) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val lifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(lifecycleOwner) {
        val lifecycle = lifecycleOwner.lifecycle
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_CREATE) {
                onComposing(
                    AppBarState(
                        title = "Home",
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
    val state by viewModel.state.collectAsStateWithLifecycle()
    HomeContent(modifier = modifier, state = state)
}

@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    state: HomeState
) {
    Box(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 10.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = if (state.dia == null) "Dia no activado" else "Dia ${state.dia.diaNro}",
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Text(text = "Total Pagado")
                    Text(
                        text = state.pagoList.sumOf { it.monto }.toString(),
                        style = MaterialTheme.typography.displaySmall
                    )
                }
            }
            LazyVerticalGrid(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                columns = GridCells.Fixed(2)
            ) {
                items(state.pagoList) { pago ->
                    PagoCard(pago = pago)
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
fun HomePreview() {
    HomeContent(
        modifier = Modifier.fillMaxSize(), state = HomeState(
            pagoList = listOf(
                PagoParticipante(
                    monto = 12.4,
                    tipoPago = 1,
                    participanteId = 1,
                    pagoId = 1,
                    diaId = 1,
                    participanteDes = "Pedro",
                    date = LocalDateTime.now()
                ),
                PagoParticipante(
                    monto = 12.4,
                    tipoPago = 1,
                    participanteId = 1,
                    pagoId = 1,
                    diaId = 1,
                    participanteDes = "Pedro",
                    date = LocalDateTime.now()
                ),
                PagoParticipante(
                    monto = 12.4,
                    tipoPago = 1,
                    participanteId = 1,
                    pagoId = 1,
                    diaId = 1,
                    participanteDes = "Pedro",
                    date = LocalDateTime.now()
                )
            )
        )
    )
}