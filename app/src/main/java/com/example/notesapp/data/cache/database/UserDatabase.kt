package com.example.notesapp.data.cache.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.notesapp.data.models.Notes

/**
 * Abstract для расширения класса (если не напишем попросить реализовать неныжные функции)
  */
@Database(entities = [Notes::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): NotesDao

}