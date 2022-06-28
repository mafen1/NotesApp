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
@Database(entities = [Notes::class, Todo::class], version = 2, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): NotesDao
    abstract fun todoDao(): TodoDao
}
val MIGRATION_1_2 = object :Migration(1,2){
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "CREATE TABLE `todo_table` (`id` INTEGER, "
                    + "`title` TEXT, `description` TEXT ," +
                    "`dataTime` TEXT, `color` TEXT , PRIMARY KEY(`id`))"
        )
    }

}