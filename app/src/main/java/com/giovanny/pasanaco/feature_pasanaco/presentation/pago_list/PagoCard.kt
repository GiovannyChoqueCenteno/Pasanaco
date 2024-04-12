package com.giovanny.pasanaco.feature_pasanaco.presentation.pago_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.giovanny.pasanaco.Screen
import com.giovanny.pasanaco.feature_pasanaco.domain.model.Pago
import com.giovanny.pasanaco.feature_pasanaco.domain.model.PagoParticipante
import java.time.LocalDateTime

@Composable
fun PagoCard(
    pago: PagoParticipante,
    onNavigate: ((String) -> Unit)? = null,
    onDeleting: (() -> Unit)? = null,
    isEditable: Boolean = false
) {
    Card {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            Text(text = pago.participanteDes)
            Text(text = if (pago.tipoPago == 1) "QR" else "EFECTIVO")
            Text(
                text = pago.createdDatTimeFormatted,
            )
            Text(
                text = pago.monto.toString() + " Bs"
            )
            if (isEditable) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier.align(Alignment.CenterEnd),
                    ) {

                        OutlinedIconButton(
                            onClick = {
                                if (onNavigate != null) {
                                    onNavigate(
                                        Screen.NuevoPago.route +
                                                "?pagoId=${pago.pagoId}"
                                    )
                                }
                            }) {
                            Icon(
                                imageVector = Icons.Outlined.Edit,
                                contentDescription = "",
                                tint = Color.Yellow
                            )

                        }
                        OutlinedIconButton(
                            onClick = {
                                onDeleting?.invoke()
                            },
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Delete,
                                contentDescription = "",
                                tint = MaterialTheme.colorScheme.error
                            )
                        }
                    }
                }
            }

        }
    }
}

@Preview
@Composable
fun PagoCardPreview() {
    return PagoCard(
        pago = PagoParticipante(
            monto = 10.0,
            tipoPago = 1,
            participanteId = 1,
            pagoId = 1,
            diaId = 1,
            participanteDes = "Desc",
            date = LocalDateTime.now()
        ),
        onNavigate = {

        },
        isEditable = false
    )
}