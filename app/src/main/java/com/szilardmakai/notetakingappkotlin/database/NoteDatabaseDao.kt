package com.szilardmakai.notetakingappkotlin.database

import androidx.paging.DataSource
import androidx.room.*

@Dao
interface NoteDatabaseDao {

    @Insert
    fun addNote(note: Note)

    @Update
    fun updateNote(note: Note)

    @Delete
    fun deleteNote(note: Note)

    @Query("SELECT * FROM notes_table ORDER BY modified_on DESC ")
    fun getNotes(): DataSource.Factory<Int, Note>
}