package com.giovanny.pasanaco.feature_pasanaco.data.datasource

import androidx.room.Dao
import androidx.room.Query
import com.giovanny.pasanaco.feature_pasanaco.domain.model.Pago
import kotlinx.coroutines.flow.Flow

@Dao
interface PagoDao {
    @Query("SELECT * FROM Pago")
    fun getPagos(): Flow<List<Pago>>
}

