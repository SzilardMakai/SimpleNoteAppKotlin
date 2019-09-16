package com.szilardmakai.notetakingappkotlin.notedetail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.szilardmakai.remindmekotlin.R

class NoteDetailViewModelFactory(
    private val app: Application
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteDetailViewModel::class.java)) {
            return NoteDetailViewModel(app) as T
        } else {
            throw IllegalArgumentException(app.getString(R.string.viewModel_error))
        }
    }
}