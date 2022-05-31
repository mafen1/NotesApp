package com.example.notesapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notesapp.data.dao.NotesDao
import com.example.notesapp.data.models.Notes

@Database(entities = [Notes::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {

    // !!
    abstract fun userDao(): NotesDao

    companion object {
        // видимость другим потокам
        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getDatabase(context: Context): UserDatabase {
            val tempInstance = INSTANCE

            if (tempInstance != null) {
                return tempInstance
            }
            //  !!
            synchronized(this){
                val instance = Room.databaseBuilder(
                    // !!
                    context.applicationContext,
                    UserDatabase::class.java,
                    "user_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }

    }
}