package com.giovanny.pasanaco.di

import android.app.Application
import androidx.room.Room
import com.giovanny.pasanaco.feature_pasanaco.data.datasource.PasanacoDatabase
import com.giovanny.pasanaco.feature_pasanaco.data.repository.DiaRepositoryImpl
import com.giovanny.pasanaco.feature_pasanaco.data.repository.PagoRepositoryImpl
import com.giovanny.pasanaco.feature_pasanaco.data.repository.ParticipanteRepositoryImpl
import com.giovanny.pasanaco.feature_pasanaco.domain.repository.DiaRepository
import com.giovanny.pasanaco.feature_pasanaco.domain.repository.PagoRepository
import com.giovanny.pasanaco.feature_pasanaco.domain.repository.ParticipanteRepository
import com.giovanny.pasanaco.feature_pasanaco.domain.use_case.dia.AddDia
import com.giovanny.pasanaco.feature_pasanaco.domain.use_case.dia.CloseDia
import com.giovanny.pasanaco.feature_pasanaco.domain.use_case.dia.DiaActivo
import com.giovanny.pasanaco.feature_pasanaco.domain.use_case.dia.DiaUseCases
import com.giovanny.pasanaco.feature_pasanaco.domain.use_case.dia.GetCountDia
import com.giovanny.pasanaco.feature_pasanaco.domain.use_case.pago.AddPago
import com.giovanny.pasanaco.feature_pasanaco.domain.use_case.pago.GetPagos
import com.giovanny.pasanaco.feature_pasanaco.domain.use_case.pago.GetPagosDiaActivo
import com.giovanny.pasanaco.feature_pasanaco.domain.use_case.pago.PagoUseCases
import com.giovanny.pasanaco.feature_pasanaco.domain.use_case.participante.AddParticipante
import com.giovanny.pasanaco.feature_pasanaco.domain.use_case.participante.DeleteParticipante
import com.giovanny.pasanaco.feature_pasanaco.domain.use_case.participante.GetPagosDiaActivoByParticipante
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
            addPago = AddPago(repository),
            getPagosDiaActivo = GetPagosDiaActivo(repository)
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
            getPariticipantes = GetPariticipantes(repository),
            addParticipante = AddParticipante(repository),
            deleteParticipante = DeleteParticipante(repository),
            getPagosDiaActivoByParticipante = GetPagosDiaActivoByParticipante(repository)
        )
    }


    @Provides
    @Singleton
    fun provideDiaRepository(db: PasanacoDatabase): DiaRepository {
        return DiaRepositoryImpl(db.diaDao())
    }

    @Provides
    @Singleton
    fun provideDiaUseCases(repository: DiaRepository): DiaUseCases {
        return DiaUseCases(
            addDia = AddDia(repository),
            diaActivo = DiaActivo(repository),
            closeDia = CloseDia(repository),
            getCountDia = GetCountDia(repository)
        )
    }
}
