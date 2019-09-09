package com.szilardmakai.notetakingappkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.szilardmakai.remindmekotlin.R

class AddNoteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)
    }
}
