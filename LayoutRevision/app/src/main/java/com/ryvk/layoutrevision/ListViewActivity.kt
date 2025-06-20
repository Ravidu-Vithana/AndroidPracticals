package com.ryvk.layoutrevision

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ListViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_list_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var wordList = arrayOf(
            "Software Engineer",
            "Development",
            "Desktop",
            "Computer",
            "Developer",
            "Business",
            "System",
            "Platform",
            "Engineering"
        )

        val arrayAdapter = ArrayAdapter(this,R.layout.dropdown_item,wordList)
        val listView: ListView = findViewById<ListView>(R.id.listView)
        listView.adapter = arrayAdapter
    }
}