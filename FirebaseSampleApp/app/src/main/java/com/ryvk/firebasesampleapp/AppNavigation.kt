package com.ryvk.firebasesampleapp

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.ryvk.firebasesampleapp.home.HomeScreen
import com.ryvk.firebasesampleapp.signIn.SignInScreen
import com.ryvk.firebasesampleapp.signUp.SignUpScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    var startDestination = ""
    startDestination = if(FirebaseAuth.getInstance().currentUser != null){
        Screen.HomeScreen.route
    }else{
        Screen.SignInScreen.route
    }

    NavHost(navController = navController, startDestination = startDestination) {
        composable (Screen.SignInScreen.route) { SignInScreen(navController) }
        composable(Screen.SignUpScreen.route) { SignUpScreen(navController) }
        composable(Screen.HomeScreen.route) { HomeScreen(navController) }
    }

}