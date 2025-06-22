package com.ryvk.androidcustomcomponents

import android.os.Bundle
import android.widget.Toast
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

        val controllerButton = findViewById<ControllerButton>(R.id.controllerButton)
        controllerButton.setOnClickListener(ControllerButton.Type.BUTTON_UP){
            Toast.makeText(this@MainActivity,"Clicked up",Toast.LENGTH_SHORT).show()
        }
        controllerButton.setOnClickListener(ControllerButton.Type.BUTTON_DOWN){
            Toast.makeText(this@MainActivity,"Clicked down",Toast.LENGTH_SHORT).show()
        }
        controllerButton.setOnClickListener(ControllerButton.Type.BUTTON_LEFT){
            Toast.makeText(this@MainActivity,"Clicked left",Toast.LENGTH_SHORT).show()
        }
        controllerButton.setOnClickListener(ControllerButton.Type.BUTTON_RIGHT){
            Toast.makeText(this@MainActivity,"Clicked right",Toast.LENGTH_SHORT).show()
        }
        controllerButton.setOnClickListener(ControllerButton.Type.BUTTON_MIDDLE){
            Toast.makeText(this@MainActivity,"Clicked middle",Toast.LENGTH_SHORT).show()
        }
    }
}