package com.szilardmakai.notetakingappkotlin.notelist

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.szilardmakai.remindmekotlin.R

class NoteListViewModelFactory(val app: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteListViewModel::class.java)) {
            return NoteListViewModel(app) as T
        } else {
            throw IllegalArgumentException(app.getString(R.string.viewModel_error))
        }
    }
}