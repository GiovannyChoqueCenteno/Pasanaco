package com.giovanny.pasanaco.feature_pasanaco.data.datasource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.giovanny.pasanaco.feature_pasanaco.domain.model.Dia
import kotlinx.coroutines.flow.Flow

@Dao
interface DiaDao {
    @Query("select * from Dia")
    fun getDias(): Flow<List<Dia>>

    @Insert
    suspend fun addDia(dia: Dia): Long

    @Query("update Dia set isActivo = 0 where diaId = :diaId")
    suspend fun closeDia(diaId: Long)

    @Query("select * from Dia where isActivo=1")
    suspend fun diaActivo(): Dia

    @Query("SELECT COUNT() from Dia")
    suspend fun getCountDia(): Int
}