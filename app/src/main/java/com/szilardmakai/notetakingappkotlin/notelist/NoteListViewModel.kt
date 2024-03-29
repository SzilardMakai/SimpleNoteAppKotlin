package com.szilardmakai.notetakingappkotlin.notelist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.szilardmakai.notetakingappkotlin.database.Note
import com.szilardmakai.notetakingappkotlin.database.NoteDatabase
import com.szilardmakai.notetakingappkotlin.repository.NoteRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class NoteListViewModel(app: Application) : AndroidViewModel(app) {

    private val viewModelJob = SupervisorJob()

    private val viewModelScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val noteRepository = NoteRepository(
        NoteDatabase.getInstance(app)
    )

    val notes = noteRepository.noteList

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