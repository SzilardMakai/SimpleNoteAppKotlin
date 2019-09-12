package com.szilardmakai.notetakingappkotlin.repository

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.szilardmakai.notetakingappkotlin.database.Note
import com.szilardmakai.notetakingappkotlin.database.NoteDatabase
import com.szilardmakai.notetakingappkotlin.database.NoteDatabaseDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

const val PAGE_INITIAL_LOAD_SIZE: Int = 20
const val PAGE_SIZE: Int = 10

class NoteRepository(private val database: NoteDatabase) {

    val noteList: LiveData<PagedList<Note>>

    init {
        val noteDatabaseDao: NoteDatabaseDao = database.noteDatabaseDao
        val pagedListConfig: PagedList.Config = (PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(PAGE_INITIAL_LOAD_SIZE)
            .setPageSize(PAGE_SIZE)
            .build())
        noteList = LivePagedListBuilder<Int, Note>(noteDatabaseDao.getNotes(), pagedListConfig)
            .build()
    }

    suspend fun addNote(note: Note) {
        withContext(Dispatchers.IO) {
            database.noteDatabaseDao.addNote(note)
        }
    }

    suspend fun getNote(noteId: Long): Note {
        return withContext(Dispatchers.IO) {
            database.noteDatabaseDao.getNote(noteId)
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
}