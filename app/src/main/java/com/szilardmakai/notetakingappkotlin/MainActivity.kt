package com.szilardmakai.notetakingappkotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.szilardmakai.remindmekotlin.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
    }
}
