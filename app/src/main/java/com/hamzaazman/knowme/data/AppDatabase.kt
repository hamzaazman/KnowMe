package com.hamzaazman.knowme.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hamzaazman.knowme.data.dao.PersonDao
import com.hamzaazman.knowme.data.model.PersonEntity

@Database(entities = [PersonEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun personDao(): PersonDao
}

