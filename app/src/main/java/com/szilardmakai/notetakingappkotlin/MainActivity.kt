package com.szilardmakai.notetakingappkotlin

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.szilardmakai.notetakingappkotlin.database.Note
import com.szilardmakai.notetakingappkotlin.database.NoteListViewMode
import com.szilardmakai.notetakingappkotlin.database.NoteListViewModelFactory
import com.szilardmakai.remindmekotlin.R
import com.szilardmakai.remindmekotlin.databinding.ActivityMainBinding

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this,
            R.layout.activity_main
        )
        setSupportActionBar(toolbar)


        recyclerView = findViewById(R.id.content_recycler_view)

        val viewModelFactory =
            NoteListViewModelFactory(this.application)
        val viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(NoteListViewMode::class.java)
        val adapter =
            NoteAdapter(NoteClickListener { nightContent ->
                Toast.makeText(this, "sausage: $nightContent", Toast.LENGTH_SHORT).show()
            })

        recyclerView.adapter = adapter

//        viewModel.addNote(Note(0, "Content"))
        viewModel.notes.observe(this, Observer {
            adapter.submitList(it)
        })

        fab.setOnClickListener {
            val intent = Intent(this, AddNoteActivity::class.java)
            startActivity(intent)
        }
    }

}
