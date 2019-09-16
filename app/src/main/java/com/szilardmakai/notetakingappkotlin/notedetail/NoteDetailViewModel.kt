package com.szilardmakai.notetakingappkotlin.notedetail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.szilardmakai.notetakingappkotlin.database.Note
import com.szilardmakai.notetakingappkotlin.database.NoteDatabase
import com.szilardmakai.notetakingappkotlin.repository.NoteRepository

class NoteDetailViewModel(
    app: Application
) : AndroidViewModel(app) {

    private val noteRepository = NoteRepository(NoteDatabase.getInstance(app))

    fun addNote(note: Note) {
        noteRepository.addNote(note)
    }

    fun updateNote(note: Note) {
        noteRepository.updateNote(note)
    }
}