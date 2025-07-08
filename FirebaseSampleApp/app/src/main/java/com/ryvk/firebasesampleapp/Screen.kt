package com.ryvk.firebasesampleapp

sealed class Screen (val route: String){
    object SignInScreen : Screen("sign_in_screen")
    object SignUpScreen : Screen("sign_up_screen")
    object HomeScreen : Screen("home_screen")
}