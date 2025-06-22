package com.ryvk.androidarchitecturecomponents.mvp

import android.content.Context

interface LoginContract {
    interface View{
        fun navigateToHome()
        fun showErrorMessage(errorMessage : String)
    }
    interface Presenter{
        fun handleLogin(context: Context,email: String,password:String)
    }
}