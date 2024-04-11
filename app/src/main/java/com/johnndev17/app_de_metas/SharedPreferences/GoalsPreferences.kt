package com.johnndev17.app_de_metas.SharedPreferences

import android.content.Context
import android.content.SharedPreferences

class GoalsPreferences(context: Context) {

    private val login: SharedPreferences = context.getSharedPreferences("LOGIN", Context.MODE_PRIVATE)

    fun saveUserName(key: String, name: String){

        login.edit().putString(key, name).apply()
    }

    fun getUserName(key: String): String{

        return login.getString(key, "") ?: ""
    }
}