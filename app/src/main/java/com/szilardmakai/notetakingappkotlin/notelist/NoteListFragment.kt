package com.szilardmakai.notetakingappkotlin.notelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.szilardmakai.notetakingappkotlin.NoteAdapter
import com.szilardmakai.notetakingappkotlin.NoteClickListener
import com.szilardmakai.remindmekotlin.R
import com.szilardmakai.remindmekotlin.databinding.NoteListFragmentBinding

class NoteListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: NoteListFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.note_list_fragment,
            container, false
        )
        val application = requireNotNull(this.activity).application

        val viewModelFactory =
            NoteListViewModelFactory(application)
        val viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(NoteListViewModel::class.java)

        binding.noteListViewmodel = viewModel

        val adapter =
            NoteAdapter(NoteClickListener { nightId ->
                findNavController().navigate(
                    NoteListFragmentDirections.actionNoteListFragmentToNoteDetailFragment(
                        nightId
                    )
                )
            })

        binding.contentRecyclerView.adapter = adapter

        viewModel.notes.observe(this, Observer {
            adapter.notes = it
        })

        createItemTouchHelper(viewModel, adapter).attachToRecyclerView(binding.contentRecyclerView)

        binding.fab.setOnClickListener { view ->
            view.findNavController().navigate(
                NoteListFragmentDirections.actionNoteListFragmentToNoteDetailFragment(0)
            )
        }
        binding.lifecycleOwner = this

        return binding.root
    }

    private fun createItemTouchHelper(
        viewModel: NoteListViewModel,
        adapter: NoteAdapter
    ): ItemTouchHelper {
        val itemTouchCallback = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val note = adapter.noteAtPosition(position)
                viewModel.deleteNote(note)
            }
        }
        return ItemTouchHelper(itemTouchCallback)
    }
}
