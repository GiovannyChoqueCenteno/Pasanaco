package com.giovanny.pasanaco.feature_pasanaco.presentation.participantes_dialog

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun ParticipantesDialog(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    onSelectedParticipante: (Long, String) -> Unit,
    viewModel: ParticipantesDialogViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    Dialog(
        onDismissRequest = {
            onDismissRequest.invoke()
        },
        ) {
        Box(
            modifier = Modifier.background(MaterialTheme.colorScheme.primaryContainer)
        ) {
            LazyColumn(
                modifier = Modifier
                    .padding(20.dp)
                    .clip(
                        RoundedCornerShape(10.dp)
                    )
            ) {
                items(state.participanteList) { participante ->
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onSelectedParticipante.invoke(
                                    participante.partipanteId!!,
                                    participante.participanteDes
                                )
                                onDismissRequest.invoke()
                            },
                        text = participante.participanteDes,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Divider()
                }
            }
        }
    }
}

