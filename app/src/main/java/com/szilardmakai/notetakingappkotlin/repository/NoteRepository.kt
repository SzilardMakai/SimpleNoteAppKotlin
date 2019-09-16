package com.szilardmakai.notetakingappkotlin.repository

import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import com.szilardmakai.notetakingappkotlin.database.Note
import com.szilardmakai.notetakingappkotlin.database.NoteDatabase
import com.szilardmakai.notetakingappkotlin.database.NoteDatabaseDao
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

const val PAGE_INITIAL_LOAD_SIZE: Int = 20
const val PAGE_SIZE: Int = 10

class NoteRepository(private val database: NoteDatabase) {

    val noteList: Observable<PagedList<Note>>

    init {
        val noteDatabaseDao: NoteDatabaseDao = database.noteDatabaseDao
        val pagedListConfig: PagedList.Config = (PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(PAGE_INITIAL_LOAD_SIZE)
            .setPageSize(PAGE_SIZE)
            .build())
        noteList = RxPagedListBuilder<Int, Note>(
            noteDatabaseDao.getNotes(),
            pagedListConfig
        ).buildObservable()
    }

    fun addNote(note: Note) {
        Completable.fromAction { database.noteDatabaseDao.addNote(note) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe()
    }

    fun updateNote(note: Note) {
        Completable.fromAction { database.noteDatabaseDao.updateNote(note) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe()
    }

    fun deleteNote(note: Note) {
        Completable.fromAction { database.noteDatabaseDao.deleteNote(note) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe()
    }
}