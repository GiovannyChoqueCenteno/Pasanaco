package com.giovanny.pasanaco.feature_pasanaco.presentation.participantes_dialog

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.giovanny.pasanaco.feature_pasanaco.domain.model.Participante

@Composable
fun ParticipantesDialog(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    onSelectedParticipante: (Long, String) -> Unit,
    viewModel: ParticipantesDialogViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    ParticipantesContent(
        state = state,
        onDismissRequest = {
            onDismissRequest.invoke()
        }, onSelectedParticipante = { participanteId, participanteDes ->
            onSelectedParticipante.invoke(participanteId, participanteDes)
        })
}

@Composable
fun ParticipantesContent(
    modifier: Modifier = Modifier,
    state: ParticipantesDialogState,
    onDismissRequest: () -> Unit,
    onSelectedParticipante: (Long, String) -> Unit
) {
    Dialog(
        onDismissRequest = {
            onDismissRequest.invoke()
        },
    ) {
        Box(
            modifier = modifier
                .heightIn(0.dp, 400.dp)
                .background(
                    MaterialTheme.colorScheme.primaryContainer, RoundedCornerShape(10.dp)
                )
                .clip(
                    RoundedCornerShape(10.dp)
                )
        ) {
            LazyColumn(
                modifier = Modifier
                    .padding(10.dp)

            ) {
                items(state.participanteList) { participante ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onSelectedParticipante.invoke(
                                    participante.participanteId!!,
                                    participante.participanteDes
                                )
                                onDismissRequest.invoke()
                            },
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(55.dp)
                        ) {
                            Text(
                                modifier = Modifier
                                    .align(Alignment.Center),
                                text = participante.participanteDes,
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                        Divider(
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }
        }
    }
}

@Composable
@Preview()
fun ParticipantesPreview() {
    ParticipantesContent(
        state = ParticipantesDialogState(
            participanteList = listOf(
                Participante(
                    participanteId = 1,
                    participanteDes = "Participante",
                    monto = 200.0,
                    selected = false,
                    estado = true
                ),
                Participante(
                    participanteId = 1,
                    participanteDes = "Participante",
                    monto = 200.0,
                    selected = false,
                    estado = true
                ),
                Participante(
                    participanteId = 1,
                    participanteDes = "Participante",
                    monto = 200.0,
                    selected = false,
                    estado = true
                ),
                Participante(
                    participanteId = 1,
                    participanteDes = "Participante",
                    monto = 200.0,
                    selected = false,
                    estado = true
                )
            )
        ),
        onDismissRequest = {

        },
        onSelectedParticipante = { id, nombre ->

        }
    )
}
