package com.ryvk.layoutrevision

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentTransaction

class FrameLayoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_frame_layout)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val button1: Button = findViewById<Button>(R.id.button6)
        button1.setOnClickListener(View.OnClickListener {
            val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.frameLayout,BlankFragmentOne(),"Fragment 1")
            fragmentTransaction.commit()
        })

        val button2: Button = findViewById<Button>(R.id.button7)
        button2.setOnClickListener(View.OnClickListener {
            val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.frameLayout,BlankFragmentTwo(),"Fragment 2")
            fragmentTransaction.commit()
        })

        val button3: Button = findViewById<Button>(R.id.button8)
        button3.setOnClickListener(View.OnClickListener {
            val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.frameLayout,BlankFragmentThree(),"Fragment 3")
            fragmentTransaction.commit()
        })

        val button4: Button = findViewById<Button>(R.id.button3)
        button4.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this@FrameLayoutActivity,TableLayoutActivity::class.java))
        })

    }
}