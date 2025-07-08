package com.ryvk.firebasesampleapp.models

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.ryvk.firebasesampleapp.APP_PACKAGE_NAME
import androidx.core.content.edit

data class User (val name : String, val email : String) {
    fun saveToSP(context: Context, user: User){
        val storage: SharedPreferences = context.getSharedPreferences(APP_PACKAGE_NAME, Context.MODE_PRIVATE)
        storage.edit (commit = true){ putString("user", Gson().toJson(user)) }
    }
}