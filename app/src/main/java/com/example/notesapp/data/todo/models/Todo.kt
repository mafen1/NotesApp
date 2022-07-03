package com.example.notesapp.data.todo.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_table")
data class Todo(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    var id: Int,
    @ColumnInfo(name="title")
    var title: String,
    @ColumnInfo(name="description")
    var description: String,
    @ColumnInfo(name="dataTime")
    var dataTime: String,
    @ColumnInfo(name="color")
    var color: String,
    @ColumnInfo(name="priority")
    var priority: Int,
    @ColumnInfo(name = "done")
    var done: Boolean = false
)
