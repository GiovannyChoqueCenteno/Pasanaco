package com.giovanny.pasanaco.feature_pasanaco.data.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.giovanny.pasanaco.core.Converters
import com.giovanny.pasanaco.feature_pasanaco.domain.model.Pago
import com.giovanny.pasanaco.feature_pasanaco.domain.model.Participante
import com.giovanny.pasanaco.feature_pasanaco.domain.model.Dia

@Database(
    entities = [Pago::class, Participante::class, Dia::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class PasanacoDatabase : RoomDatabase() {
    abstract fun pagoDao(): PagoDao
    abstract fun participanteDao(): ParticipanteDao
    abstract fun diaDao(): DiaDao

    companion object {
        const val DATABASE_NAME = "PASANACO"
    }
}