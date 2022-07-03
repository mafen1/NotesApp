package com.example.notesapp.data.notes.cache.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.notesapp.data.notes.models.Notes
import com.example.notesapp.data.todo.cache.database.TodoDao
import com.example.notesapp.data.todo.models.Todo

/**
 * Abstract для расширения класса (если не напишем попросить реализовать неныжные функции)
 */
@Database(entities = [Notes::class, Todo::class], version = 4, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): NotesDao
    abstract fun todoDao(): TodoDao
}

val MIGRATION_2_3 = object : Migration(3,4) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE 'todo_table' ADD COLUMN `done` INTEGER NOT NULL default 0")
    }
}