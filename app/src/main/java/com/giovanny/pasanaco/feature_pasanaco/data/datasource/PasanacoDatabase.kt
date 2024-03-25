package com.giovanny.pasanaco.feature_pasanaco.data.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.giovanny.pasanaco.feature_pasanaco.domain.model.Pago

@Database(
    entities = [Pago::class],
    version = 1
)
abstract class PasanacoDatabase : RoomDatabase() {
    abstract val pagoDao: PagoDao

    companion object {
        const val DATABASE_NAME = "PASANACO"
    }
}