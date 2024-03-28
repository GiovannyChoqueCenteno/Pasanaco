package com.giovanny.pasanaco.feature_pasanaco.presentation.pago_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
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

@Composable
fun PagoListScreen(
    onComposing: (AppBarState) -> Unit,
    navController: NavController,
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
                                    navController.navigate(Screen.NuevoPago.route)
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

    val state by viewModel.state.collectAsStateWithLifecycle()
    Box(
        modifier = modifier
    ) {
        LazyColumn(
        ) {
            items(state.pagoList) { pago ->
                Card {
                    Column {
                        Text(text = "Luis Perez")
                        Text(text = "Descripcion")
                        Text(text = "123.2 Bs.")
                    }
                }
            }
        }
        if (state.isLoading) {
            CircularProgressIndicator()
        }
    }
}

