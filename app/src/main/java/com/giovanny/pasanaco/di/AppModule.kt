package com.giovanny.pasanaco.di

import android.app.Application
import androidx.room.Room
import com.giovanny.pasanaco.feature_pasanaco.data.datasource.PasanacoDatabase
import com.giovanny.pasanaco.feature_pasanaco.data.repository.PagoRepositoryImpl
import com.giovanny.pasanaco.feature_pasanaco.domain.repository.PagoRepository
import com.giovanny.pasanaco.feature_pasanaco.domain.use_case.GetPagos
import com.giovanny.pasanaco.feature_pasanaco.domain.use_case.PagoUseCases
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
        return PagoRepositoryImpl(db.pagoDao)
    }

    @Provides
    @Singleton
    fun providePagoUseCases(repository: PagoRepository): PagoUseCases {
        return PagoUseCases(
            getPagos = GetPagos(repository)
        )
    }
}