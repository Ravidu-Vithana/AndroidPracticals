package com.ryvk.androidarchitecturecomponents.mvp

import android.content.Context
import com.ryvk.androidarchitecturecomponents.model.User
import com.ryvk.androidarchitecturecomponents.mvc.MvcActivity

class LoginPresenter (private val view: LoginContract.View) : LoginContract.Presenter {
    override fun handleLogin(context: Context,email: String, password: String) {
        if(email.isBlank() || password.isBlank()){
            view.showErrorMessage("Invalid email or password")
        }else{
            val user = User()
            user.loginUser(
                context = context,
                email = email,
                password = password
            )
            view.navigateToHome()
        }
    }
}