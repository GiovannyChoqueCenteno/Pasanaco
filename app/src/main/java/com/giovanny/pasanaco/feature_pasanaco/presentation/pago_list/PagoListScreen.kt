package com.giovanny.pasanaco.feature_pasanaco.presentation.pago_list

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun PagoListScreen(
    viewModel: PagoListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    LazyColumn() {
        items(state.pagoList) { pago ->
            Text(text = pago.descripcion)
        }
    }
}