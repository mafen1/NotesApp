package com.example.notesapp.tree

interface ItemKotlin {
    fun name(): String
    fun img(): String
    fun showStage(progress: Int): String
    fun showStage(value: Int, max: Int): String
}