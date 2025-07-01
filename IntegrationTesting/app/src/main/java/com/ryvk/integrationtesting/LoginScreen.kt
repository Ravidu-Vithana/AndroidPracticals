package com.ryvk.integrationtesting

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ryvk.integrationtesting.ui.theme.IntegrationTestingTheme

@Preview
@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    IntegrationTestingTheme {
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var isLoading by remember { mutableStateOf(false) }
        var errorMessage by remember { mutableStateOf<String?>(null) }
        var emailError by remember { mutableStateOf<String?>(null) }
        var passwordError by remember { mutableStateOf<String?>(null) }

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(24.dp)
                .testTag("loginScreen"),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (errorMessage != null) {
                Text(
                    text = errorMessage!!,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                        .testTag("errorMessage")
                )
            }

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                isError = emailError != null,
                supportingText = {
                    if (emailError != null) {
                        Text(text = emailError!!, color = MaterialTheme.colorScheme.error)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("emailField"),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                isError = passwordError != null,
                supportingText = {
                    if (passwordError != null) {
                        Text(text = passwordError!!, color = MaterialTheme.colorScheme.error)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("passwordField"),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )

            Spacer(modifier = Modifier.height(24.dp))

            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier.testTag("loadingIndicator"))
            } else {
                Button(
                    onClick = {
                        // Reset errors
                        emailError = null
                        passwordError = null
                        errorMessage = null

                        // Validate inputs
                        if (email.isEmpty()) {
                            emailError = "Email cannot be empty"
                            return@Button
                        }
                        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                            emailError = "Please enter a valid email"
                            return@Button
                        }
                        if (password.isEmpty()) {
                            passwordError = "Password cannot be empty"
                            return@Button
                        }
                        if (password.length < 6) {
                            passwordError = "Password must be at least 6 characters"
                            return@Button
                        }

                        // Simulate login
                        isLoading = true
                        if (email == "test@example.com" && password == "password123") {
                            onLoginSuccess()
                        } else {
                            errorMessage = "Invalid credentials"
                        }
                        isLoading = false
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("loginButton")
                ) {
                    Text("Login")
                }
            }
        }
    }
}