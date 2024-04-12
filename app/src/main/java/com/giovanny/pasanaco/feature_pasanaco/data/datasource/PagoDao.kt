package com.giovanny.pasanaco.feature_pasanaco.data.datasource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.giovanny.pasanaco.feature_pasanaco.domain.model.Pago
import com.giovanny.pasanaco.feature_pasanaco.domain.model.PagoParticipante
import kotlinx.coroutines.flow.Flow

@Dao
interface PagoDao {
    @Query("SELECT Pago.*,Participante.participanteDes FROM Pago,Participante where Pago.participanteId=Participante.participanteId")
    fun getPagos(): Flow<List<PagoParticipante>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPago(pago: Pago)

    @Query(
        "SELECT Pago.*,Participante.participanteDes FROM Pago,Participante,Dia" +
                " where Pago.participanteId=Participante.participanteId and Dia.diaId = Pago.diaId and Dia.isActivo = 1"
    )
    fun getPagosDiaActivo(): Flow<List<PagoParticipante>>

    @Query("delete from Pago where pagoId=:pagoId")
    suspend fun deletePago(pagoId: Long): Int

    @Query(
        "SELECT Pago.*,Participante.participanteDes FROM Pago,Participante " +
                "where Pago.participanteId=Participante.participanteId and Pago.pagoId=:pagoId"
    )
    suspend fun getPago(pagoId: Long): PagoParticipante?
}

