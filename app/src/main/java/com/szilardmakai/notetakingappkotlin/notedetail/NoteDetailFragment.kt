package com.szilardmakai.notetakingappkotlin.notedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.szilardmakai.notetakingappkotlin.database.Note
import com.szilardmakai.remindmekotlin.databinding.NoteDetailFragmentBinding

class NoteDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = NoteDetailFragmentBinding.inflate(inflater)


        val application = requireNotNull(this.activity).application
        val arguments = NoteDetailFragmentArgs.fromBundle(arguments!!)

        val viewModelFactory = NoteDetailViewModelFactory(
            application
        )
        val viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(NoteDetailViewModel::class.java)

        binding.viewModel = viewModel
        binding.etAddNoteText.requestFocus()


        binding.note = viewModel.loadNote(arguments.noteId)

        binding.btnAddNoteSave.setOnClickListener {
            val note = binding.note
            if (arguments.noteId != 0L) {
                note?.content = binding.etAddNoteText.text.toString()
                note?.modificationTime = System.currentTimeMillis()
                viewModel.updateNote(note!!)
            } else {
                val newNote = Note()
                newNote.content = binding.etAddNoteText.text.toString()
                viewModel.addNote(newNote)
            }
            findNavController().navigate(NoteDetailFragmentDirections.actionNoteDetailFragmentToNoteListFragment())
            binding.etAddNoteText.clearFocus()
        }

        binding.btnAddNoteCancel.setOnClickListener {
            findNavController().navigate(NoteDetailFragmentDirections.actionNoteDetailFragmentToNoteListFragment())
            binding.etAddNoteText.clearFocus()
        }
        binding.lifecycleOwner = this

        return binding.root
    }
}
