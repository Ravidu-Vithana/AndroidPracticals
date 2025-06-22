package com.ryvk.androidarchitecturecomponents.mvc

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ryvk.androidarchitecturecomponents.R
import com.ryvk.androidarchitecturecomponents.databinding.ActivityMvcBinding
import com.ryvk.androidarchitecturecomponents.model.User

class MvcActivity : AppCompatActivity() {

    private val binding : ActivityMvcBinding by lazy {
        ActivityMvcBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.loginButton.setOnClickListener {
            val user = User()
            user.loginUser(
                context = this@MvcActivity,
                email = binding.editTextTextEmailAddress.text.toString(),
                password = binding.editTextTextPassword.text.toString()
            )
        }

    }
}