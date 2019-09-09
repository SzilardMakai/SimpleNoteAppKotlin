package com.szilardmakai.notetakingappkotlin.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.szilardmakai.notetakingappkotlin.database.Note
import com.szilardmakai.notetakingappkotlin.database.NoteDatabase
import com.szilardmakai.notetakingappkotlin.database.NoteDatabaseDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NoteRepository(private val database: NoteDatabase) {
    private val PAGE_INITIAL_LOAD_SIZE: Int = 20
    private val PAGE_SIZE: Int = 10

    private var noteDatabaseDao: NoteDatabaseDao
    var noteList: LiveData<PagedList<Note>>

    init {
        noteDatabaseDao = database.noteDatabaseDao
        val pagedListConfig: PagedList.Config = (PagedList.Config.Builder()
            .setInitialLoadSizeHint(PAGE_INITIAL_LOAD_SIZE)
            .setPageSize(PAGE_SIZE)
            .setEnablePlaceholders(false)
            .build())
        noteList = LivePagedListBuilder<Int, Note>(noteDatabaseDao.getNotes(), pagedListConfig)
            .build()
//        populateDb()
    }

    suspend fun addNote(note: Note) {
        withContext(Dispatchers.IO) {
            database.noteDatabaseDao.addNote(note)
        }
    }

    suspend fun updateNote(note: Note) {
        withContext(Dispatchers.IO) {
            database.noteDatabaseDao.updateNote(note)
        }
    }

    suspend fun deleteNote(note: Note) {
        withContext(Dispatchers.IO) {
            database.noteDatabaseDao.deleteNote(note)
        }
    }

    fun populateDb() {
        CoroutineScope(Dispatchers.IO).launch {
            addNote(Note(0, "content stuff"))
        }
    }
}