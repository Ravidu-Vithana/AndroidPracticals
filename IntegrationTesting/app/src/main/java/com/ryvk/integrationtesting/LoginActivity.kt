package com.ryvk.integrationtesting

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.ryvk.integrationtesting.ui.theme.IntegrationTestingTheme

class LoginActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IntegrationTestingTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                        .background(MaterialTheme.colorScheme.background),
                ) { innerPadding ->
                    Column (
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize(),
                    ) {
                        LoginScreen(
                            onLoginSuccess = {
                                Toast.makeText(this@LoginActivity, "Login successful", Toast.LENGTH_SHORT).show()
                            }
                        )
                    }
                }
            }
        }
    }
}