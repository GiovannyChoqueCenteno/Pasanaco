package com.giovanny.pasanaco.feature_pasanaco.data.datasource

import androidx.room.Dao
import androidx.room.Query
import com.giovanny.pasanaco.feature_pasanaco.domain.model.Pago
import com.giovanny.pasanaco.feature_pasanaco.domain.model.Participante
import kotlinx.coroutines.flow.Flow

@Dao
interface ParticipanteDao {
    @Query("SELECT * FROM Participante")
    fun getParticipantes(): Flow<List<Participante>>
}