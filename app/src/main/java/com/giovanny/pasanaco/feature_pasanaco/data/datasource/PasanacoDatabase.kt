package com.giovanny.pasanaco.feature_pasanaco.data.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.giovanny.pasanaco.feature_pasanaco.domain.model.Pago
import com.giovanny.pasanaco.feature_pasanaco.domain.model.Participante

@Database(
    entities = [Pago::class, Participante::class],
    version = 1
)
abstract class PasanacoDatabase : RoomDatabase() {
    abstract fun pagoDao(): PagoDao
    abstract fun participanteDao(): ParticipanteDao


    companion object {
        const val DATABASE_NAME = "PASANACO"
    }
}