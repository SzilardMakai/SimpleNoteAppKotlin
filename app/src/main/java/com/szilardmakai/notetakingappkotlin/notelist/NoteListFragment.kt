package com.szilardmakai.notetakingappkotlin.notelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.szilardmakai.notetakingappkotlin.NoteAdapter
import com.szilardmakai.notetakingappkotlin.NoteClickListener
import com.szilardmakai.remindmekotlin.R
import com.szilardmakai.remindmekotlin.databinding.NoteListFragmentBinding

class NoteListFragment : Fragment() {

    companion object {
        fun newInstance() = NoteListFragment()
    }

//    private lateinit var viewModel: NoteListViewModel

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
        val adapter =
            NoteAdapter(NoteClickListener { nightContent ->
                Toast.makeText(application, "sausage: $nightContent", Toast.LENGTH_SHORT).show()
            })

        binding.contentRecyclerView.adapter = adapter

//        viewModel.addNote(Note(0, "Content"))
        viewModel.notes.observe(this, Observer {
            adapter.submitList(it)
        })

        binding.fab.setOnClickListener {view ->
            view.findNavController().navigate(
                NoteListFragmentDirections.actionNoteListFragmentToNoteDetailFragment(0)
            )
        }

        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProviders.of(this).get(NoteListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
