package com.giovanny.pasanaco.di

import android.app.Application
import androidx.room.Room
import com.giovanny.pasanaco.feature_pasanaco.data.datasource.PasanacoDatabase
import com.giovanny.pasanaco.feature_pasanaco.data.repository.PagoRepositoryImpl
import com.giovanny.pasanaco.feature_pasanaco.data.repository.ParticipanteRepositoryImpl
import com.giovanny.pasanaco.feature_pasanaco.domain.repository.PagoRepository
import com.giovanny.pasanaco.feature_pasanaco.domain.repository.ParticipanteRepository
import com.giovanny.pasanaco.feature_pasanaco.domain.use_case.pago.AddPago
import com.giovanny.pasanaco.feature_pasanaco.domain.use_case.pago.GetPagos
import com.giovanny.pasanaco.feature_pasanaco.domain.use_case.pago.PagoUseCases
import com.giovanny.pasanaco.feature_pasanaco.domain.use_case.participante.GetPariticipantes
import com.giovanny.pasanaco.feature_pasanaco.domain.use_case.participante.ParticipanteUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providePasanaconDatabase(app: Application): PasanacoDatabase {
        return Room.databaseBuilder(
            app,
            PasanacoDatabase::class.java,
            PasanacoDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun providePagoRepository(db: PasanacoDatabase): PagoRepository {
        return PagoRepositoryImpl(db.pagoDao())
    }

    @Provides
    @Singleton
    fun providePagoUseCases(repository: PagoRepository): PagoUseCases {
        return PagoUseCases(
            getPagos = GetPagos(repository),
            addPago = AddPago(repository)
        )
    }

    @Provides
    @Singleton
    fun provideParticipanteRepository(db: PasanacoDatabase): ParticipanteRepository {
        return ParticipanteRepositoryImpl(db.participanteDao())
    }

    @Provides
    @Singleton
    fun provideParticipanteUseCases(repository: ParticipanteRepository): ParticipanteUseCases {
        return ParticipanteUseCases(
            getPariticipantes = GetPariticipantes(repository)
        )
    }
}