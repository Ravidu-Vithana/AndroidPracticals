package com.ryvk.androidarchitecturecomponents.mvp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ryvk.androidarchitecturecomponents.R
import com.ryvk.androidarchitecturecomponents.databinding.ActivityMvcBinding
import com.ryvk.androidarchitecturecomponents.model.User
import com.ryvk.androidarchitecturecomponents.mvc.MvcActivity

class LoginActivity : AppCompatActivity() , LoginContract.View {

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

        val presenter = LoginPresenter(this)
        binding.loginButton.setOnClickListener {
            val email = binding.editTextTextEmailAddress.text.toString()
            val password = binding.editTextTextPassword.text.toString()
            presenter.handleLogin(this@LoginActivity,email,password)
        }

    }

    override fun navigateToHome() {
        Toast.makeText(this@LoginActivity,"Navigating to home", Toast.LENGTH_LONG).show()
    }

    override fun showErrorMessage(errorMessage: String) {
        Toast.makeText(this@LoginActivity,"An error occurred. Please try again.", Toast.LENGTH_LONG).show()
    }


}