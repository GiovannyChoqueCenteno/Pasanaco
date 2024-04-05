package com.giovanny.pasanaco.feature_pasanaco.presentation.options

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.OpenInNew
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.OpenInNew
import androidx.compose.material.icons.outlined.Payment
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.giovanny.pasanaco.Screen
import com.giovanny.pasanaco.core.AppBarState

@Composable
fun OptionScreen(
    onComposing: (AppBarState) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: OptionViewModel = hiltViewModel(),
    navController: NavController
) {
    val lifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(lifecycleOwner) {
        val lifecycle = lifecycleOwner.lifecycle
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_START) {
                onComposing(
                    AppBarState(
                        title = "Opciones",
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
    OptionContent(
        modifier = modifier,
        state = state,
        onEvent = {
            viewModel.onEvent(it)
        },
        onNavigate = {
            navController.navigate(it)
        }
    )

}

@Composable
fun OptionContent(
    modifier: Modifier = Modifier,
    state: OptionState,
    onEvent: (OptionEvent) -> Unit,
    onNavigate: (String) -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.align(Alignment.TopCenter),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    ),
                    modifier = Modifier
                        .size(130.dp)
                        .clickable {
                            onEvent.invoke(OptionEvent.ToggleDialog)
                        }
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                imageVector = if (state.diaActivo != null)
                                    Icons.Outlined.Close
                                else
                                    Icons.AutoMirrored.Outlined.OpenInNew,
                                contentDescription = ""
                            )
                            Text(
                                text = if (state.diaActivo != null) "Cerrar Dia"
                                else "Abrir Dia",
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.width(20.dp))
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    ),
                    modifier = Modifier
                        .size(130.dp)
                        .clickable {
                            onNavigate.invoke(Screen.Participantes.route)
                        }
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Person,
                                contentDescription = ""
                            )
                            Text(text = "Participantes")
                        }
                    }
                }
            }
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    ),
                    modifier = Modifier
                        .size(130.dp)
                        .clickable {
                            onEvent(OptionEvent.NewPasanaco)
                        }
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.AddCircle,
                                contentDescription = ""
                            )
                            Text(
                                text = "Nuevo Pasanaco",
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.width(20.dp))
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    ),
                    modifier = Modifier
                        .size(130.dp)
                        .clickable {
                            onNavigate(Screen.PagoParticipante.route)
                        }
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Payment,
                                contentDescription = ""
                            )
                            Text(
                                text = "Pagos por Participante",
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
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
                    onEvent(OptionEvent.ToggleDialog)
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            onEvent.invoke(OptionEvent.ToggleDialog)
                        }
                    ) {
                        Text("Cancelar")
                    }
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            onEvent.invoke(OptionEvent.OpenDia)
                        }
                    ) {
                        Text("Aceptar")
                    }
                },
            )
    }
}

@Preview
@Composable
fun OptionPreview() {
    OptionContent(
        modifier = Modifier.fillMaxSize(),
        state = OptionState(
            diaActivo = null
        ),
        onEvent = {

        },
        onNavigate = {

        }
    )
}