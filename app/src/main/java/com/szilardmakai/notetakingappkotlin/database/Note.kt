package com.szilardmakai.notetakingappkotlin.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_table")
data class Note(
    @PrimaryKey(autoGenerate = true)
    var noteId: Long = 0L,

    @ColumnInfo(name = "content")
    val content: String,

    @ColumnInfo(name = "created_on")
    val creationTime: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "modified_on")
    val modificationTime: Long = 0L
)
