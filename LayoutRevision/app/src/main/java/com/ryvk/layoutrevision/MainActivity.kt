package com.ryvk.layoutrevision

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val loginButton: Button = findViewById<Button>(R.id.button)
        loginButton.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this@MainActivity,LinearLayoutActivity::class.java))
        })

        val goToDay3: Button = findViewById<Button>(R.id.button4)
        goToDay3.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this@MainActivity,DayThreeActivity::class.java))
        })

    }
}