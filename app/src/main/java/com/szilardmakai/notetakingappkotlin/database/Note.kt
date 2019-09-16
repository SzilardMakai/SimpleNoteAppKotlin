package com.szilardmakai.notetakingappkotlin.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
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
): Parcelable