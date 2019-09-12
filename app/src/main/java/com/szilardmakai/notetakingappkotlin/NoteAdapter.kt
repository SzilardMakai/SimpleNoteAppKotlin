package com.szilardmakai.notetakingappkotlin

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.szilardmakai.notetakingappkotlin.database.Note
import com.szilardmakai.remindmekotlin.R
import com.szilardmakai.remindmekotlin.databinding.NoteListItemBinding

class NoteAdapter(val clickListener: NoteClickListener) :
    PagedListAdapter<Note, NoteAdapter.NoteViewHolder>(
        NoteDiffCallback()
    ) {
    var notes: List<Note> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val withDataBinding: NoteListItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.note_list_item, parent, false
        )
        return NoteViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(notes[position], clickListener)
    }

    override fun getItemCount(): Int {
        return notes.size
    }


    fun noteAtPosition(position: Int): Note {
        return notes[position]
    }

    class NoteViewHolder(val binding: NoteListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(note: Note, clickListener: NoteClickListener) {
            binding.note = note
            binding.clickListener = clickListener
            binding.tvNoteItemListContent.text = note.content
            binding.tvNoteItemListLastModified.text =
                Util.convertLongToDateString(
                    if (note.modificationTime == 0L) {
                        note.creationTime
                    } else {
                        note.modificationTime
                    }
                )
            binding.executePendingBindings()
        }
    }
}

class NoteDiffCallback : DiffUtil.ItemCallback<Note>() {
    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem.noteId == newItem.noteId
    }
}

class NoteClickListener(val clickListener: (noteId: Long) -> Unit) {
    fun onClick(note: Note) = clickListener(note.noteId)
}