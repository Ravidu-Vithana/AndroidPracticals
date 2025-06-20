package com.ryvk.layoutrevision

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class LinearLayoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_linear_layout)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        for (i in 0 until 4) {
            Log.d("for loop", "onCreate: $i")
            val itemCardFragment = ItemCardFragment()
            fragmentTransaction.add(R.id.linearLayout,itemCardFragment,"Fragment $i")
        }
        fragmentTransaction.commit()

        val button: Button = findViewById<Button>(R.id.button3)
        button.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this@LinearLayoutActivity,FrameLayoutActivity::class.java))
        })

    }
}