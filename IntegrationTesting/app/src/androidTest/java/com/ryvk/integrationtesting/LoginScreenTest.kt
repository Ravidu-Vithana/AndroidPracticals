package com.ryvk.integrationtesting

import androidx.compose.ui.test.assert
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class LoginScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testLoginScreenElementsVisibility() {
        composeTestRule.setContent {
            LoginScreen()
        }

        composeTestRule.onNodeWithTag("loginScreen").assertExists()
        composeTestRule.onNodeWithTag("emailField").assertExists()
        composeTestRule.onNodeWithTag("passwordField").assertExists()
        composeTestRule.onNodeWithTag("loginButton").assertExists()
    }

//    @Test
//    fun testEmptyEmailError() {
//        composeTestRule.setContent {
//            LoginScreen()
//        }
//
//        composeTestRule.onNodeWithTag("passwordField").performTextInput("password123")
//        composeTestRule.onNodeWithTag("loginButton").performClick()
//
//        composeTestRule.onNodeWithTag("errorMessage").assert(hasText("Invalid credentials"))
//    }

    @Test
    fun testSuccessfulLogin() {
        var loginSuccess = false
        composeTestRule.setContent {
            LoginScreen(onLoginSuccess = { loginSuccess = true })
        }

        composeTestRule.onNodeWithTag("emailField").performTextInput("test@example.com")
        composeTestRule.onNodeWithTag("passwordField").performTextInput("password123")
        composeTestRule.onNodeWithTag("loginButton").performClick()

        assert(loginSuccess)
    }

    @Test
    fun testUnsuccessfulLogin() {
        var loginSuccess = false
        composeTestRule.setContent {
            LoginScreen(onLoginSuccess = { loginSuccess = true })
        }
        composeTestRule.onNodeWithTag("emailField").performTextInput("test@example.com")
        composeTestRule.onNodeWithTag("passwordField").performTextInput("wrongpassword")
        composeTestRule.onNodeWithTag("loginButton").performClick()
        assert(!loginSuccess)
    }
}