package com.ryvk.androidarchitecturecomponents.mvvm

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.ryvk.androidarchitecturecomponents.R
import com.ryvk.androidarchitecturecomponents.mvc.MvcActivity
import com.ryvk.androidarchitecturecomponents.mvp.LoginActivity

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
        val textView: TextView = findViewById(R.id.textView)
        val button : Button = findViewById(R.id.button)
        val button2 : Button = findViewById(R.id.button2)
        val button3 : Button = findViewById(R.id.button3)
        val button5 : Button = findViewById(R.id.button5)

//        var number = 0;
//
//        textView.text = number.toString()
//
//        button.setOnClickListener{
//            number++
//            textView.text = number.toString()
//        }

        val viewModel: MainActivityViewModel = ViewModelProvider(this@MainActivity).get(MainActivityViewModel::class.java)

        textView.text = viewModel.number.toString()

        button.setOnClickListener{
            viewModel.number++
            textView.text = viewModel.number.toString()
        }

        button2.setOnClickListener{
            startActivity(Intent(this@MainActivity, LiveDataActivity::class.java))
        }

        button3.setOnClickListener{
            startActivity(Intent(this@MainActivity, MvcActivity::class.java))
        }

        button5.setOnClickListener{
            startActivity(Intent(this@MainActivity, LoginActivity::class.java))
        }

    }
}