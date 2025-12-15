package com.hamzaazman.knowme.di

import android.app.Application
import androidx.room.Room
import com.hamzaazman.knowme.data.AppDatabase
import com.hamzaazman.knowme.data.dao.PersonDao
import com.hamzaazman.knowme.data.repository.PersonRepositoryImpl
import com.hamzaazman.knowme.domain.repository.PersonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): AppDatabase =
        Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            "persons.db"
        ).build()

    @Provides @Singleton
    fun providePersonDao(db: AppDatabase) = db.personDao()

    @Provides @Singleton
    fun provideRepository(dao: PersonDao): PersonRepository =
        PersonRepositoryImpl(dao)
}
