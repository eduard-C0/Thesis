package com.example.musicstreaming.utils

import android.content.Context
import android.content.SharedPreferences
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit){
    this.addTextChangedListener(object  : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }
    })
}

fun saveStringIntoSharedPreferences(context: Context, key: String, value: String){
    val sharedPreferences = context.getSharedPreferences("application",Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    editor.putString(key,value)
    editor.apply()
}

fun readStringFromSharedPreferences(context: Context, key: String): String?{
    val sharedPreferences = context.getSharedPreferences("application",Context.MODE_PRIVATE)
    return sharedPreferences.getString(key, "")
}