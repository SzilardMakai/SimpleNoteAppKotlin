package com.szilardmakai.notetakingappkotlin.notelist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.szilardmakai.notetakingappkotlin.database.Note
import com.szilardmakai.notetakingappkotlin.database.NoteDatabase
import com.szilardmakai.notetakingappkotlin.repository.NoteRepository

class NoteListViewModel(app: Application) : AndroidViewModel(app) {

    private val noteRepository = NoteRepository(
        NoteDatabase.getInstance(app)
    )

    val notes = noteRepository.noteList

    fun deleteNote(note: Note) {
            noteRepository.deleteNote(note)
    }
}