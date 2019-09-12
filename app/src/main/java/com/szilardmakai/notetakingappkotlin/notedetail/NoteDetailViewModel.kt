package com.szilardmakai.notetakingappkotlin.notedetail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.szilardmakai.notetakingappkotlin.database.Note
import com.szilardmakai.notetakingappkotlin.database.NoteDatabase
import com.szilardmakai.notetakingappkotlin.repository.NoteRepository
import kotlinx.coroutines.*

class NoteDetailViewModel(
    app: Application
) : AndroidViewModel(app) {

    private val noteRepository = NoteRepository(NoteDatabase.getInstance(app))

    private val viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)


    fun addNote(note: Note) {
        uiScope.launch {
            withContext(Dispatchers.IO) {
                noteRepository.addNote(note)
            }
        }
    }

    private fun getNote(noteId: Long): Note {
        return runBlocking(Dispatchers.IO) { noteRepository.getNote(noteId) }

    }

    fun loadNote(noteId: Long): Note? {
        return if(noteId != 0L) {
            getNote(noteId)
        } else {
            null
        }
    }


    fun updateNote(note: Note) {
        uiScope.launch {
            withContext(Dispatchers.IO) {
                noteRepository.updateNote(note)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}