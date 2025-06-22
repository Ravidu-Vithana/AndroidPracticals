package com.ryvk.androidarchitecturecomponents.model

import android.content.Context
import android.util.Log
import android.widget.Toast

class User (){
    private var name: String? = null
    private var email: String? = null
    private var password: String? = null

    constructor(name: String, email: String, password: String) : this() {
        this.name = name
        this.email = email
        this.password = password
    }
    fun loginUser(context: Context, email: String , password: String){
        Toast.makeText(context, "Logging user with email: $email and password $password", Toast.LENGTH_LONG).show()
    }
}