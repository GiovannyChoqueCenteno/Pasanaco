package com.giovanny.pasanaco.feature_pasanaco.data.datasource

import androidx.room.Dao
import androidx.room.Query
import com.giovanny.pasanaco.feature_pasanaco.domain.model.Dia
import kotlinx.coroutines.flow.Flow

@Dao
interface PasanacoDao {
    @Query("SELECT * FROM Dia")
    fun addPasanaco(): Flow<List<Dia>>
}