package com.giovanny.pasanaco.feature_pasanaco.data.datasource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.giovanny.pasanaco.feature_pasanaco.domain.model.PagoParticipanteDia
import com.giovanny.pasanaco.feature_pasanaco.domain.model.Participante
import kotlinx.coroutines.flow.Flow

@Dao
interface ParticipanteDao {
    @Query("SELECT * FROM Participante")
    fun getParticipantes(): Flow<List<Participante>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addParticipante(participante: Participante)

    @Query("delete from Participante where participanteId =:participanteId")
    suspend fun deleteParticipante(participanteId: Long): Int

    @Query(
        "select Participante.participanteId,Participante.participanteDes,Participante.monto ," +
                " IFNULL(Pagos.pago,0) as pago from Participante left join" +
                "(select sum(Pago.monto) as pago,Participante.participanteId" +
                " from Pago,Participante,Dia " +
                "where Pago.participanteId=Participante.participanteId" +
                " AND Dia.diaId = Pago.diaId AND Dia.isActivo=1 group by Participante.participanteId) as Pagos" +
                " ON Participante.participanteId = Pagos.participanteId "

    )
    fun getPagosDiaActivoByParticipante(): Flow<List<PagoParticipanteDia>>

    @Query("select * from participante where participanteId=:participanteId")
    suspend fun getParticipante(participanteId: Long): Participante?
}