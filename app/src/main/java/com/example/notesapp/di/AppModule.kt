package com.example.notesapp.di

import android.content.Context
import androidx.room.Room
import com.example.notesapp.data.notes.cache.database.UserDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context : Context) : UserDatabase =
        Room.databaseBuilder(context, UserDatabase::class.java, "app_database").build()

    @Provides
    @Singleton
    fun provideNoteDao(appDatabase: UserDatabase) = appDatabase.userDao()

}