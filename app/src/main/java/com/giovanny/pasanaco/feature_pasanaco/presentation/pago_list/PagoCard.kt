package com.giovanny.pasanaco.feature_pasanaco.presentation.pago_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.giovanny.pasanaco.feature_pasanaco.domain.model.Pago
import com.giovanny.pasanaco.feature_pasanaco.domain.model.PagoParticipante

@Composable
fun PagoCard(
    pago: PagoParticipante
) {
    Card {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            Text(text = pago.participanteDes)
            Text(text = if (pago.tipoPago == 1) "QR" else "EFECTIVO")
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = pago.createdDatTimeFormatted,
                    modifier = Modifier.align(Alignment.CenterStart)
                )
            }
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    text = pago.monto.toString() + " Bs"
                )
            }
        }
    }
}