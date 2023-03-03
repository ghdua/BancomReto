package com.example.bancom.UI.Session

import android.content.Context
import android.content.SharedPreferences

class LoginPreferences {
    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor
    lateinit var context: Context
    var PRIVATE_MODE: Int = 0

    companion object {
        val PREFERENCE_NAME = "DataSesion"
        val Key_EMail = "EMail"
    }

    fun GuardarDataSP(EMail: String){
        editor.putString(Key_EMail, EMail)
        editor.commit()
    }

    constructor(context: Context){
        this.context = context
        sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, PRIVATE_MODE)
        editor = sharedPreferences.edit()
    }


    fun VerificarLogin(): String{
        var email = sharedPreferences.getString(Key_EMail, null)
        if(email!=null)
            return email
        else return ""
    }

    fun BorrarData(){
        editor.clear()
        editor.remove(Key_EMail)
        editor.commit()
    }
}