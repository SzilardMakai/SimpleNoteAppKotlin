package com.szilardmakai.notetakingappkotlin.database

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class NoteListViewModelFactory(val app: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteListViewMode::class.java)) {
            return NoteListViewMode(app) as T
        } else {
            throw IllegalArgumentException("Unable to construct ViewModel")
        }
    }
}