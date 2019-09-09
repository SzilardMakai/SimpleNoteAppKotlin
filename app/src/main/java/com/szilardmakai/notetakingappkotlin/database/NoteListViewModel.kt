package com.szilardmakai.notetakingappkotlin.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.szilardmakai.notetakingappkotlin.repository.NoteRepository
import kotlinx.coroutines.*

class NoteListViewMode(app: Application): AndroidViewModel(app) {

    private val viewModelJob = SupervisorJob()

    private val viewModelScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val noteRepository = NoteRepository(
        NoteDatabase.getInstance(app)
    )

    val notes = noteRepository.noteList

    fun addNote(note: Note) {
        viewModelScope.launch {
            noteRepository.addNote(note)
        }
    }

    fun updateNote(note: Note) {
        viewModelScope.launch {
            noteRepository.updateNote(note)
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            noteRepository.deleteNote(note)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}