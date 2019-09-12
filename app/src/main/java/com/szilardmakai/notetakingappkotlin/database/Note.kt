package com.szilardmakai.notetakingappkotlin.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_table")
data class Note (
    @PrimaryKey(autoGenerate = true)
    var noteId: Long = 0L,

    @ColumnInfo(name = "content")
    var content: String = "",

    @ColumnInfo(name = "created_on")
    val creationTime: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "modified_on")
    var modificationTime: Long = creationTime
)